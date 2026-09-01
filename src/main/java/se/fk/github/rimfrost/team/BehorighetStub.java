package se.fk.github.rimfrost.team;

import java.util.Map;

/**
 * Hardcoded stub of handläggare → SID-behörighet, using the same identities as
 * {@link TeamController}'s stub data. Intended to allow consumers (t.ex. OUL) to integrera
 * mot riktiga handläggare-identiteter innan en riktig behörighetskälla finns på plats.
 */
final class BehorighetStub
{

   /** Maps individ varde → har SID-behörighet. */
   private static final Map<String, Boolean> HANDLAGGARE_SID_PERMISSION = Map.of(
         "111111111", true,
         "222222222", false,
         "333333333", false);

   private BehorighetStub()
   {
   }

   /**
    * Returns whether the given handläggare has SID-behörighet. Okänd identitet (fel
    * {@code idTyp} eller okänt {@code idVarde}) ger {@code false}, samma som en känd
    * handläggare utan SID-behörighet.
    *
    * @param idTyp typen av identifierare; måste matcha stubbens {@code TYP_ID}
    * @param idVarde identifierarens värde
    * @return {@code true} om handläggaren har SID-behörighet, annars {@code false}
    */
   static Boolean hasSidPermission(String idTyp, String idVarde)
   {
      if (!HandlaggareIdentitet.isKnownTyp(idTyp) || idVarde == null)
      {
         return false;
      }
      return HANDLAGGARE_SID_PERMISSION.getOrDefault(idVarde, false);
   }
}
