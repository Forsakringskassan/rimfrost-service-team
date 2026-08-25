package se.fk.github.rimfrost.team;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

/**
 * Integration tests for {@link TeamController}, covering all cases in {@code krav.md}
 * (TEAM-FR-01 and TEAM-FR-02).
 */
@QuarkusTest
class TeamControllerTest
{

   private static final String TYP_ID = "116759e4-18fd-4209-849c-90abbd257d22";

   // TEAM-FR-01 — GET /team/{teamId}/individer

   /** TEAM-FR-01.1 */
   @Test
   void getTeamIndivider_teamStockholm_returnsEmptyList()
   {
      given()
            .when().get("/team/1/individer")
            .then()
            .statusCode(200)
            .body("individer", empty());
   }

   /** TEAM-FR-01.2 */
   @Test
   void getTeamIndivider_teamGoteborg_returnsIndividAAndC()
   {
      given()
            .when().get("/team/2/individer")
            .then()
            .statusCode(200)
            .body("individer", hasSize(2))
            .body("individer.varde", containsInAnyOrder("111111111", "333333333"))
            .body("individer.typId", containsInAnyOrder(TYP_ID, TYP_ID));
   }

   /** TEAM-FR-01.3 */
   @Test
   void getTeamIndivider_teamMalmo_returnsIndividB()
   {
      given()
            .when().get("/team/3/individer")
            .then()
            .statusCode(200)
            .body("individer", hasSize(1))
            .body("individer[0].varde", is("222222222"))
            .body("individer[0].typId", is(TYP_ID));
   }

   /** TEAM-FR-01.4 */
   @Test
   void getTeamIndivider_teamUppsala_returnsIndividAAndB()
   {
      given()
            .when().get("/team/4/individer")
            .then()
            .statusCode(200)
            .body("individer", hasSize(2))
            .body("individer.varde", containsInAnyOrder("111111111", "222222222"));
   }

   /** TEAM-FR-01.5 */
   @Test
   void getTeamIndivider_unknownTeam_returns404()
   {
      given()
            .when().get("/team/99/individer")
            .then()
            .statusCode(404);
   }

   // TEAM-FR-02 — GET /individ/{idTyp}/{idVarde}/team

   /** TEAM-FR-02.1 */
   @Test
   void getIndividTeam_individA_returnsGoteborgAndUppsala()
   {
      given()
            .when().get("/individ/" + TYP_ID + "/111111111/team")
            .then()
            .statusCode(200)
            .body("team", hasSize(2))
            .body("team.id", containsInAnyOrder(2, 4))
            .body("team.namn", containsInAnyOrder("Team Göteborg", "Team Uppsala"));
   }

   /** TEAM-FR-02.2 */
   @Test
   void getIndividTeam_individB_returnsMalmoAndUppsala()
   {
      given()
            .when().get("/individ/" + TYP_ID + "/222222222/team")
            .then()
            .statusCode(200)
            .body("team", hasSize(2))
            .body("team.id", containsInAnyOrder(3, 4))
            .body("team.namn", containsInAnyOrder("Team Malmö", "Team Uppsala"));
   }

   /** TEAM-FR-02.3 */
   @Test
   void getIndividTeam_individC_returnsGoteborg()
   {
      given()
            .when().get("/individ/" + TYP_ID + "/333333333/team")
            .then()
            .statusCode(200)
            .body("team", hasSize(1))
            .body("team[0].id", is(2))
            .body("team[0].namn", is("Team Göteborg"));
   }

   /** TEAM-FR-02.4 */
   @Test
   void getIndividTeam_unknownVarde_returns404()
   {
      given()
            .when().get("/individ/" + TYP_ID + "/999999999/team")
            .then()
            .statusCode(404);
   }

   /** TEAM-FR-02.5 */
   @Test
   void getIndividTeam_wrongTypId_returns404()
   {
      given()
            .when().get("/individ/wrong-typ-id/111111111/team")
            .then()
            .statusCode(404);
   }

   // GET /individ/{idTyp}/{idVarde}/behorigheter (FKPOC-931)

   @Test
   void getBehorigheter_individA_returnsSid()
   {
      given()
            .when().get("/individ/" + TYP_ID + "/111111111/behorigheter")
            .then()
            .statusCode(200)
            .body("behorigheter", hasSize(1))
            .body("behorigheter[0]", is("SID"));
   }

   @Test
   void getBehorigheter_individB_returnsEmptyList()
   {
      given()
            .when().get("/individ/" + TYP_ID + "/222222222/behorigheter")
            .then()
            .statusCode(200)
            .body("behorigheter", empty());
   }

   @Test
   void getBehorigheter_unknownVarde_returns404()
   {
      given()
            .when().get("/individ/" + TYP_ID + "/999999999/behorigheter")
            .then()
            .statusCode(404);
   }

   @Test
   void getBehorigheter_wrongTypId_returns404()
   {
      given()
            .when().get("/individ/wrong-typ-id/111111111/behorigheter")
            .then()
            .statusCode(404);
   }
}
