package se.fk.github.rimfrost.team;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import se.fk.rimfrost.team.jaxrsspec.controllers.generatedsource.TeamControllerApi;
import se.fk.rimfrost.team.jaxrsspec.controllers.generatedsource.model.GetIndividTeamResponse;
import se.fk.rimfrost.team.jaxrsspec.controllers.generatedsource.model.GetTeamMembersResponse;
import se.fk.rimfrost.team.jaxrsspec.controllers.generatedsource.model.Idtyp;
import se.fk.rimfrost.team.jaxrsspec.controllers.generatedsource.model.Team;

/**
 * Hardcoded stub implementation of {@link TeamControllerApi}.
 *
 * <p>Returns static test data as defined in {@code krav.md} (TEAM-FR-01, TEAM-FR-02).
 * Intended to allow consumers to integrate against a real HTTP endpoint before a live
 * data source is available.
 */
@ApplicationScoped
@Path("")
public class TeamController implements TeamControllerApi
{

   private static final UUID TYP_ID = HandlaggareIdentitet.TYP_ID;

   private static final Idtyp INDIVID_A = new Idtyp(TYP_ID, "111111111");
   private static final Idtyp INDIVID_B = new Idtyp(TYP_ID, "222222222");
   private static final Idtyp INDIVID_C = new Idtyp(TYP_ID, "333333333");

   private static final Team TEAM_STOCKHOLM = new Team(1, "Team Stockholm", "Stockholm");
   private static final Team TEAM_GOTEBORG = new Team(2, "Team Göteborg", "Göteborg");
   private static final Team TEAM_MALMO = new Team(3, "Team Malmö", "Malmö");
   private static final Team TEAM_UPPSALA = new Team(4, "Team Uppsala", "Uppsala");

   /** Maps teamId → members (TEAM-FR-01). */
   private static final Map<Integer, List<Idtyp>> TEAM_MEMBERS = Map.of(
         1, List.of(),
         2, List.of(INDIVID_A, INDIVID_C),
         3, List.of(INDIVID_B),
         4, List.of(INDIVID_A, INDIVID_B));

   /** Maps individ varde → teams (TEAM-FR-02). */
   private static final Map<String, List<Team>> INDIVID_TEAMS = Map.of(
         "111111111", List.of(TEAM_GOTEBORG, TEAM_UPPSALA),
         "222222222", List.of(TEAM_MALMO, TEAM_UPPSALA),
         "333333333", List.of(TEAM_GOTEBORG));

   /** Known teamIds, used for 404 detection. */
   private static final Set<Integer> KNOWN_TEAM_IDS = TEAM_MEMBERS.keySet();

   /**
    * Returns all members of the given team (TEAM-FR-01).
    *
    * @param teamId the team's unique ID
    * @return response containing the team's members
    * @throws NotFoundException if teamId is not recognised
    */
   @Override
   public GetTeamMembersResponse getTeamIndivider(Integer teamId)
   {
      if (!KNOWN_TEAM_IDS.contains(teamId))
      {
         throw new NotFoundException("Team not found: " + teamId);
      }
      return new GetTeamMembersResponse(TEAM_MEMBERS.get(teamId));
   }

   /**
    * Returns all teams the individ belongs to (TEAM-FR-02).
    *
    * @param idTyp the type of identifier; must match the stub's {@code TYP_ID}
    * @param idVarde the identifier value
    * @return response containing the individ's teams
    * @throws NotFoundException if idTyp or idVarde is not recognised
    */
   @Override
   public GetIndividTeamResponse getIndividTeam(String idTyp, String idVarde)
   {
      if (!HandlaggareIdentitet.isKnownTyp(idTyp))
      {
         throw new NotFoundException("Unknown idTyp: " + idTyp);
      }
      List<Team> teams = INDIVID_TEAMS.get(idVarde);
      if (teams == null)
      {
         throw new NotFoundException("Individ not found: " + idVarde);
      }
      return new GetIndividTeamResponse(teams);
   }

   /**
    * Returns whether the given handläggare has SID-behörighet (FKPOC-931).
    *
    * @param idTyp the type of identifier; must match the stub's {@code TYP_ID}
    * @param idVarde the identifier value
    * @return {@code true} if the handläggare has SID-behörighet, {@code false} otherwise
    * @throws NotFoundException if idTyp or idVarde is not recognised
    */
   @Override
   public Boolean hasSidPermission(String idTyp, String idVarde)
   {
      Boolean hasSidPermission = BehorighetStub.hasSidPermission(idTyp, idVarde);
      if (hasSidPermission == null)
      {
         throw new NotFoundException("Handläggare not found: idTyp=" + idTyp + ", idVarde=" + idVarde);
      }
      return hasSidPermission;
   }
}
