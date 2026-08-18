# Plan: FKPOC-922 — Validate idTyp in GET /individ/{idTyp}/{idVarde}/team

## Background

TEAM-FR-02.5 requires that `GET /individ/{idTyp}/{idVarde}/team` returns HTTP 404 when
`idTyp` does not match the stub's `typId`. Currently the stub ignores `idTyp` entirely.
This change also aligns the stub's `typId` with the `HANDLAGGARE_ID` typId used by the
integration test infrastructure (`116759e4-18fd-4209-849c-90abbd257d22`), as decided in
the FKPOC-921 plan, so that OUL can call the team service using the same typId it extracts
from Bearer tokens.

---

## Approach

Three files change — controller, test, and krav. All changes are mechanical: one constant
value, one guard clause, one new test case.

---

## Steps

### Step 1 — Update typId and activate validation in TeamController

**`src/main/java/se/fk/github/rimfrost/team/TeamController.java`**

- Change `TYP_ID` to `UUID.fromString("116759e4-18fd-4209-849c-90abbd257d22")`.
- Add typId guard to `getIndividTeam` and remove the `(ignored in stub)` Javadoc note:

```java
@Override
public GetIndividTeamResponse getIndividTeam(String idTyp, String idVarde)
{
   if (!TYP_ID.toString().equals(idTyp))
   {
      throw new NotFoundException("Individ not found: " + idTyp + "/" + idVarde);
   }
   List<Team> teams = INDIVID_TEAMS.get(idVarde);
   if (teams == null)
   {
      throw new NotFoundException("Individ not found: " + idVarde);
   }
   return new GetIndividTeamResponse(teams);
}
```

---

### Step 2 — Update TeamControllerTest

**`src/test/java/se/fk/github/rimfrost/team/TeamControllerTest.java`**

- Change `TYP_ID` constant to `"116759e4-18fd-4209-849c-90abbd257d22"`.
- Update all existing test URLs to use the new typId value (currently hardcoded as `stub`
  in path params — replace with `TYP_ID`).
- Add new test for TEAM-FR-02.5:

```java
/** TEAM-FR-02.5 */
@Test
void getIndividTeam_wrongTypId_returns404()
{
   given()
         .when().get("/individ/wrong-typ-id/111111111/team")
         .then()
         .statusCode(404);
}
```

---

### Step 3 — Update testdata typId in krav.md

**`krav.md`** — update the `typId` column in the Individer testdata table from
`4c34906c-03d9-425f-9a1a-062ef6eb88c7` to `116759e4-18fd-4209-849c-90abbd257d22`.

---

## Execution order

1 → 2 → 3
