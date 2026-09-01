package se.fk.github.rimfrost.team;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link BehorighetStub}. Testar stubben direkt, separat från
 * {@code TeamControllerTest}:s täckning av REST-endpointet (FKPOC-931).
 */
class BehorighetStubTest
{

   private static final String TYP_ID = "116759e4-18fd-4209-849c-90abbd257d22";

   @Test
   void hasSidPermission_individA_returnsTrue()
   {
      Boolean hasSidPermission = BehorighetStub.hasSidPermission(TYP_ID, "111111111");
      assertThat(hasSidPermission, is(true));
   }

   @Test
   void hasSidPermission_individB_returnsFalse()
   {
      Boolean hasSidPermission = BehorighetStub.hasSidPermission(TYP_ID, "222222222");
      assertThat(hasSidPermission, is(false));
   }

   @Test
   void hasSidPermission_unknownVarde_returnsFalse()
   {
      Boolean hasSidPermission = BehorighetStub.hasSidPermission(TYP_ID, "999999999");
      assertThat(hasSidPermission, is(false));
   }

   @Test
   void hasSidPermission_wrongTypId_returnsFalse()
   {
      Boolean hasSidPermission = BehorighetStub.hasSidPermission("wrong-typ-id", "111111111");
      assertThat(hasSidPermission, is(false));
   }

   @Test
   void hasSidPermission_nullIdTyp_returnsFalse()
   {
      Boolean hasSidPermission = BehorighetStub.hasSidPermission(null, "111111111");
      assertThat(hasSidPermission, is(false));
   }

   @Test
   void hasSidPermission_nullIdVarde_returnsFalse()
   {
      Boolean hasSidPermission = BehorighetStub.hasSidPermission(TYP_ID, null);
      assertThat(hasSidPermission, is(false));
   }
}
