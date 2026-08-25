package se.fk.github.rimfrost.team;

import java.util.UUID;

/**
 * Delad validering av handläggar-identiteter (typId/varde), använd av både
 * {@link TeamController} och {@link BehorighetStub}, så att stubbarna inte kan
 * hamna i otakt om vilken typId som räknas som en känd handläggare.
 */
final class HandlaggareIdentitet
{

   static final UUID TYP_ID = UUID.fromString("116759e4-18fd-4209-849c-90abbd257d22");

   private HandlaggareIdentitet()
   {
   }

   /**
    * Returns whether the given idTyp matches the known handläggare-typId. Kastar aldrig —
    * ett {@code null}-värde eller en sträng som inte går att tolka som ett UUID ger helt
    * enkelt {@code false}.
    *
    * @param idTyp typen av identifierare att validera
    * @return true om idTyp matchar den kända handläggare-typId:n
    */
   static boolean isKnownTyp(String idTyp)
   {
      if (idTyp == null)
      {
         return false;
      }
      try
      {
         return TYP_ID.equals(UUID.fromString(idTyp));
      }
      catch (IllegalArgumentException e)
      {
         return false;
      }
   }
}
