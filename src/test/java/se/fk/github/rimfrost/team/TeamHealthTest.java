package se.fk.github.rimfrost.team;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Verifies that the liveness health endpoint is available. */
@QuarkusTest
class TeamHealthTest
{

   @Test
   @DisplayName("TEAM-NFR-01.1: Tillgänglighet — /q/health/live returnerar 200 med status UP")
   void testHealthEndpoint()
   {
      when()
            .get("/q/health/live")
            .then()
            .statusCode(200)
            .body("status", is("UP"));
   }
}
