package se.fk.github.rimfrost.team;

import java.util.List;
import java.util.Map;
import se.fk.rimfrost.team.jaxrsspec.controllers.generatedsource.model.Behorighet;

/**
 * Hardcoded stub of handläggare → behörigheter, using the same identities as
 * {@link TeamController}'s stub data. Intended to allow consumers (t.ex. OUL) to integrera
 * mot riktiga handläggare-identiteter innan en riktig behörighetskälla finns på plats.
 *
 * <p>Använder API-kontraktets genererade {@link Behorighet}-typ direkt, samma mönster som
 * {@code TeamController} redan använder för {@code Idtyp}/{@code Team} — ingen egen,
 * parallell domän-enum som kan hamna i otakt med kontraktet.
 */
final class BehorighetStub
{

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
      if (!HandlaggareIdentitet.isKnownTyp(idTyp) || idVarde == null)
      {
         return null;
      }
      return HANDLAGGARE_BEHORIGHETER.get(idVarde);
   }
}
