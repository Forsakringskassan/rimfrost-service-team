# Plan: FKPOC-916 — Hardcoded team data stub

## Background

`rimfrost-service-team` is a new Quarkus service that implements the Team API defined in
`rimfrost-service-team-openapi`. The goal of this ticket is to stand up a working service that
returns hardcoded test data for four teams and three individer, as specified in `krav.md`, so
that consumers (e.g. OUL) can integrate against a real HTTP endpoint before a live data source
exists.

---

## Approach

Bootstrap a minimal Quarkus JAX-RS service. Implement `TeamControllerApi` directly in the
controller with hardcoded in-memory data — no persistence, no service layer. Write
`@QuarkusTest` tests for both endpoints covering all cases in `krav.md`.

---

## Steps

### Step 1 — Bootstrap Maven/Quarkus project

**`pom.xml`** (new file at repo root)

Minimal Quarkus service inheriting from `fk-maven-quarkus-parent`. Dependencies:
- `io.quarkus:quarkus-arc`
- `io.quarkus:quarkus-rest-jackson`
- `io.quarkus:quarkus-hibernate-validator`
- `se.fk.rimfrost:rimfrost-service-team-openapi-jaxrs-spec:0.0.1`

Test dependencies:
- `io.quarkus:quarkus-junit5`
- `io.rest-assured:rest-assured`

**`src/main/resources/application.properties`** (new file) — empty or minimal Quarkus config.

---

### Step 2 — Implement TeamController

**New file:**
`src/main/java/se/fk/github/rimfrost/team/TeamController.java`

Implements `TeamControllerApi`. Hardcoded data matching `krav.md`:

```java
@ApplicationScoped
@Path("")
public class TeamController implements TeamControllerApi {

    // individer: A=111111111, B=222222222, C=333333333 (all same typId)
    // teams: 1=Stockholm(none), 2=Göteborg(A,C), 3=Malmö(B), 4=Uppsala(A,B)

    @Override
    public GetTeamMembersResponse getTeamIndivider(Integer teamId) {
        // return hardcoded list or throw NotFoundException for unknown teamId
    }

    @Override
    public GetIndividTeamResponse getIndividTeam(String idTyp, String idVarde) {
        // return hardcoded list or throw NotFoundException for unknown idVarde
    }
}
```

Throw `jakarta.ws.rs.NotFoundException` for unknown IDs (produces HTTP 404).

---

### Step 3 — Write @QuarkusTest tests

**New file:**
`src/test/java/se/fk/github/rimfrost/team/TeamControllerTest.java`

Covers all requirements from `krav.md`:

**TEAM-FR-01 — getTeamIndivider:**
- `teamId=1` → 200, `individer` is empty
- `teamId=2` → 200, Individ A and Individ C
- `teamId=3` → 200, Individ B
- `teamId=4` → 200, Individ A and Individ B
- `teamId=99` → 404

**TEAM-FR-02 — getIndividTeam:**
- `varde=111111111` → 200, Team Göteborg (2) and Team Uppsala (4)
- `varde=222222222` → 200, Team Malmö (3) and Team Uppsala (4)
- `varde=333333333` → 200, Team Göteborg (2)
- unknown `idVarde` → 404

---

## Execution order

1 → 2 → 3
