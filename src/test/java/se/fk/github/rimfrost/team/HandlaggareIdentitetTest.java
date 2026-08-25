package se.fk.github.rimfrost.team;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HandlaggareIdentitet}.
 */
class HandlaggareIdentitetTest
{

   private static final String TYP_ID = "116759e4-18fd-4209-849c-90abbd257d22";

   @Test
   void isKnownTyp_knownTypId_returnsTrue()
   {
      assertThat(HandlaggareIdentitet.isKnownTyp(TYP_ID), is(true));
   }

   @Test
   void isKnownTyp_unknownTypId_returnsFalse()
   {
      assertThat(HandlaggareIdentitet.isKnownTyp("00000000-0000-0000-0000-000000000000"), is(false));
   }

   @Test
   void isKnownTyp_malformedTypId_returnsFalse()
   {
      assertThat(HandlaggareIdentitet.isKnownTyp("wrong-typ-id"), is(false));
   }

   @Test
   void isKnownTyp_nullTypId_returnsFalse()
   {
      assertThat(HandlaggareIdentitet.isKnownTyp(null), is(false));
   }
}
