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
    * Returns whether the given handläggare has SID-behörighet, or {@code null} if identiteten
    * inte är känd (fel {@code idTyp} eller okänt {@code idVarde}) — skiljt från en känd
    * handläggare utan SID-behörighet, som ger {@code false}.
    *
    * @param idTyp typen av identifierare; måste matcha stubbens {@code TYP_ID}
    * @param idVarde identifierarens värde
    * @return {@code true}/{@code false} om identiteten är känd, annars {@code null}
    */
   static Boolean hasSidPermission(String idTyp, String idVarde)
   {
      if (!HandlaggareIdentitet.isKnownTyp(idTyp) || idVarde == null)
      {
         return null;
      }
      return HANDLAGGARE_SID_PERMISSION.get(idVarde);
   }
}
