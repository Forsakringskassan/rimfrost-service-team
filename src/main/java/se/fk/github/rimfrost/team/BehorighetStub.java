package se.fk.github.rimfrost.team;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Hardcoded stub of handläggare → behörigheter, using the same identities as
 * {@link TeamController}'s stub data. Intended to allow consumers (t.ex. OUL) to integrera
 * mot riktiga handläggare-identiteter innan en riktig behörighetskälla finns på plats.
 */
final class BehorighetStub
{

   private static final UUID TYP_ID = UUID.fromString("116759e4-18fd-4209-849c-90abbd257d22");

   /** Maps individ varde → behörigheter. */
   private static final Map<String, List<Behorighet>> HANDLAGGARE_BEHORIGHETER = Map.of(
         "111111111", List.of(Behorighet.SID),
         "222222222", List.of(),
         "333333333", List.of());

   private BehorighetStub()
   {
   }

   /**
    * Returns the given handläggare's behörigheter, or {@code null} if identiteten inte är
    * känd (fel {@code idTyp} eller okänt {@code idVarde}) — skiljt från en känd handläggare
    * utan några behörigheter, som ger en tom lista.
    *
    * @param idTyp typen av identifierare; måste matcha stubbens {@code TYP_ID}
    * @param idVarde identifierarens värde
    * @return handläggarens behörigheter, eller {@code null} om identiteten inte är känd
    */
   static List<Behorighet> getBehorigheter(String idTyp, String idVarde)
   {
      try
      {
         if (!TYP_ID.equals(UUID.fromString(idTyp)))
         {
            return null;
         }
      }
      catch (IllegalArgumentException e)
      {
         return null;
      }
      return HANDLAGGARE_BEHORIGHETER.get(idVarde);
   }
}
