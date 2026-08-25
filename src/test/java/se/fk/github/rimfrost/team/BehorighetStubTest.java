package se.fk.github.rimfrost.team;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;
import org.junit.jupiter.api.Test;
import se.fk.rimfrost.team.jaxrsspec.controllers.generatedsource.model.Behorighet;

/**
 * Unit tests for {@link BehorighetStub}. Testar stubben direkt, separat från
 * {@code TeamControllerTest}:s täckning av REST-endpointet (FKPOC-931).
 */
class BehorighetStubTest
{

   private static final String TYP_ID = "116759e4-18fd-4209-849c-90abbd257d22";

   @Test
   void getBehorigheter_individA_returnsSid()
   {
      List<Behorighet> behorigheter = BehorighetStub.getBehorigheter(TYP_ID, "111111111");
      assertThat(behorigheter, contains(Behorighet.SID));
   }

   @Test
   void getBehorigheter_individB_returnsEmptyList()
   {
      List<Behorighet> behorigheter = BehorighetStub.getBehorigheter(TYP_ID, "222222222");
      assertThat(behorigheter, empty());
   }

   @Test
   void getBehorigheter_unknownVarde_returnsNull()
   {
      List<Behorighet> behorigheter = BehorighetStub.getBehorigheter(TYP_ID, "999999999");
      assertThat(behorigheter, nullValue());
   }

   @Test
   void getBehorigheter_wrongTypId_returnsNull()
   {
      List<Behorighet> behorigheter = BehorighetStub.getBehorigheter("wrong-typ-id", "111111111");
      assertThat(behorigheter, nullValue());
   }

   @Test
   void getBehorigheter_nullIdTyp_returnsNull()
   {
      List<Behorighet> behorigheter = BehorighetStub.getBehorigheter(null, "111111111");
      assertThat(behorigheter, nullValue());
   }

   @Test
   void getBehorigheter_nullIdVarde_returnsNull()
   {
      List<Behorighet> behorigheter = BehorighetStub.getBehorigheter(TYP_ID, null);
      assertThat(behorigheter, nullValue());
   }
}
