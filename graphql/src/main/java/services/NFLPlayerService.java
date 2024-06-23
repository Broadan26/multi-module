package services;

import data.NFLPlayer;
import data.NFLTeam;
import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import util.PlayerPosition;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NFLPlayerService {

    private List<NFLPlayer> nflPlayerList = new ArrayList<>();
    private List<NFLTeam> nflTeamList = new ArrayList<>();

    public NFLPlayerService() {
        // Temporary method
        this.implementData();
    }

    /**
     * Gets the full list of NFL Players.
     * @return Full list of current NFL Players
     */
    public List<NFLPlayer> getAllNFLPlayers() {
        return this.nflPlayerList;
    }

    /**
     * Gets an NFL Player by their ID. If an invalid ID is provided then only null is returned.
     * @param playerId ID of the NFL Player being queried
     * @return The NFL Player matching the ID or null if not found
     */
    public NFLPlayer getPlayerById(long playerId) {
        return nflPlayerList.stream()
                .filter(nflPlayer -> nflPlayer.getPlayerId() == playerId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets NFL Players by their name. Collects information based on provided information.
     * If both first and last name provided, collects on both fields.
     * If only one of first and last name are provided, collects on the provided field.
     * If none are provided, collects all players.
     * @param firstName First name of the player to query for
     * @param lastName Last name of the player to query for
     * @return A list of NFL players with a matching first name and/or last name
     */
    public List<NFLPlayer> getPlayerByName(@Nullable String firstName, @Nullable String lastName) {
        if (firstName != null && lastName != null && !firstName.isEmpty() && !lastName.isEmpty()) {
            return this.nflPlayerList.stream()
                    .filter(nflPlayer -> nflPlayer.getFirstName().equals(firstName)
                            && nflPlayer.getLastName().equals(lastName))
                    .toList();
        } else if (firstName != null && !firstName.isEmpty()) {
            return nflPlayerList.stream()
                    .filter(nflPlayer -> nflPlayer.getFirstName().equals(firstName))
                    .toList();
        } else if (lastName != null && !lastName.isEmpty()) {
            return nflPlayerList.stream()
                    .filter(nflPlayer -> nflPlayer.getFirstName().equals(firstName))
                    .toList();
        } else {
            return this.nflPlayerList;
        }
    }

    /**
     * Gets NFL Players by their current team.
     * Collects information taking into account an order of operation. ID > Team Name > No Arguments.
     * @param teamId ID of the team to query on
     * @param teamName Name of the team to query on
     * @return A list of players currently playing for the provided team
     */
    public List<NFLPlayer> getPlayersByTeam(@Nullable Long teamId, @Nullable String teamName) {
        if (teamId != null) {
            return nflPlayerList.stream()
                    .filter(nflPlayer -> nflPlayer.getCurrentTeam().getTeamId().equals(teamId))
                    .toList();
        } else if (teamName != null && !teamName.isEmpty()) {
            return this.nflPlayerList.stream()
                    .filter(nflPlayer -> nflPlayer.getCurrentTeam().getName().equals(teamName))
                    .toList();
        } else {
            return this.nflPlayerList;
        }
    }

    /**
     * Temporary method to instantiate some players for querying
     */
    private void implementData() {
        NFLTeam arizonaCardinals = new NFLTeam();
        arizonaCardinals.setTeamId(1L);
        arizonaCardinals.setName("Cardinals");
        arizonaCardinals.setCityLocation("Glendale");
        arizonaCardinals.setStateLocation("Arizona");
        arizonaCardinals.setStadiumName("State Farm Stadium");

        NFLTeam detroitLions = new NFLTeam();
        detroitLions.setTeamId(2L);
        detroitLions.setName("Lions");
        detroitLions.setCityLocation("Detroit");
        detroitLions.setStateLocation("Michigan");
        detroitLions.setStadiumName("Ford Field");

        NFLTeam sanFran49ers = new NFLTeam();
        sanFran49ers.setTeamId(3L);
        sanFran49ers.setName("49ers");
        sanFran49ers.setCityLocation("Santa Clara");
        sanFran49ers.setStateLocation("California");
        sanFran49ers.setStadiumName("Levi's Stadium");

        nflTeamList.add(arizonaCardinals);
        nflTeamList.add(detroitLions);
        nflTeamList.add(sanFran49ers);

        NFLPlayer kylerMurray = new NFLPlayer();
        kylerMurray.setPlayerId(1L);
        kylerMurray.setFirstName("Kyler");
        kylerMurray.setLastName("Murray");
        kylerMurray.setJerseyNumber(1);
        kylerMurray.setPlayerPosition(PlayerPosition.QUARTERBACK);
        kylerMurray.setCurrentTeam(arizonaCardinals);

        NFLPlayer jamesConner = new NFLPlayer();
        jamesConner.setPlayerId(2L);
        jamesConner.setFirstName("James");
        jamesConner.setLastName("Conner");
        jamesConner.setJerseyNumber(6);
        jamesConner.setPlayerPosition(PlayerPosition.HALFBACK);
        jamesConner.setCurrentTeam(arizonaCardinals);

        NFLPlayer marvinHarrisonJr = new NFLPlayer();
        marvinHarrisonJr.setPlayerId(3L);
        marvinHarrisonJr.setFirstName("Marvin");
        marvinHarrisonJr.setLastName("Harrison Jr.");
        marvinHarrisonJr.setJerseyNumber(18);
        marvinHarrisonJr.setPlayerPosition(PlayerPosition.WIDE_RECEIVER);
        marvinHarrisonJr.setCurrentTeam(arizonaCardinals);

        NFLPlayer treyMcBride = new NFLPlayer();
        treyMcBride.setPlayerId(4L);
        treyMcBride.setFirstName("Trey");
        treyMcBride.setLastName("McBride");
        treyMcBride.setJerseyNumber(85);
        treyMcBride.setPlayerPosition(PlayerPosition.TIGHT_END);
        treyMcBride.setCurrentTeam(arizonaCardinals);

        NFLPlayer jaredGoff = new NFLPlayer();
        jaredGoff.setPlayerId(5L);
        jaredGoff.setFirstName("Jared");
        jaredGoff.setLastName("Goff");
        jaredGoff.setJerseyNumber(16);
        jaredGoff.setPlayerPosition(PlayerPosition.QUARTERBACK);
        jaredGoff.setCurrentTeam(detroitLions);

        NFLPlayer davidMontgomery = new NFLPlayer();
        davidMontgomery.setPlayerId(6L);
        davidMontgomery.setFirstName("David");
        davidMontgomery.setLastName("Montgomery");
        davidMontgomery.setJerseyNumber(5);
        davidMontgomery.setPlayerPosition(PlayerPosition.HALFBACK);
        davidMontgomery.setCurrentTeam(detroitLions);

        nflPlayerList.add(kylerMurray);
        nflPlayerList.add(jamesConner);
        nflPlayerList.add(marvinHarrisonJr);
        nflPlayerList.add(treyMcBride);
        nflPlayerList.add(jaredGoff);
        nflPlayerList.add(davidMontgomery);
    }

}
