package api;

import data.NFLPlayer;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.NFLPlayerService;

import java.util.List;

/**
 * GraphQL API for NFL Players
 */
@GraphQLApi
public class NFLPlayerResource {

    private static final Logger LOG = LoggerFactory.getLogger(NFLPlayerResource.class);
    private final NFLPlayerService nflPlayerService;

    public NFLPlayerResource(NFLPlayerService nflPlayerService) {
        this.nflPlayerService = nflPlayerService;
    }

    /**
     * Queries the full list of NFL Players
     * @return A list of all current NFL Players
     */
    @Query("allPlayers")
    @Description("Get all players currently in the NFL")
    public List<NFLPlayer> getAllPlayers() {
        LOG.info("Getting all players currently in the NFL");
        return this.nflPlayerService.getAllNFLPlayers();
    }

    /**
     * Gets an NFL player by their ID
     * @param playerId ID of the player to query for
     * @return The NFL player associated with the provided ID
     */
    @Query("playerById")
    @Description("Get an NFL player by their player ID")
    public NFLPlayer getPlayerById(@Name("playerId") long playerId) {
        LOG.info("Getting an NFL player with ID: {}", playerId);
        return this.nflPlayerService.getPlayerById(playerId);
    }

    /**
     * Gets NFL players by their first and/or their last name
     * @param firstName The first name of the NFL player to find
     * @param lastName The last name of the NFL player to find
     * @return A list of NFL players that match the provided first and last name criteria
     */
    @Query("playersByName")
    @Description("Get NFL players by their first and/or last name")
    public List<NFLPlayer> getPlayersByName(@Name("firstName") String firstName,
                                            @Name("lastName") String lastName) {
        LOG.info("Getting NFL players with first name: {} and last name: {}", firstName, lastName);
        return this.nflPlayerService.getPlayerByName(firstName, lastName);
    }

    /**
     * Gets NFL players by the team they currently play for
     * @param teamId The ID of the team to query on
     * @param teamName The name of the team to query on
     * @return The list of NFL players that currently play for the provided NFL team
     */
    @Query("playersByTeam")
    @Description("Get NFL players by their team")
    public List<NFLPlayer> getPlayersByTeam(@Name("teamId") Long teamId,
                                            @Name("teamName") String teamName) {
        LOG.info("Getting NFL players with team id: {} and team name: {}", teamId, teamName);
        return this.nflPlayerService.getPlayersByTeam(teamId, teamName);
    }

}
