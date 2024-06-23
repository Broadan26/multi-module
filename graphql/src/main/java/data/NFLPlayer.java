package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Model for an NFL Player
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NFLPlayer {

    private Long playerId;
    private String firstName;
    private String lastName;
    private Integer jerseyNumber;
    private String playerPosition;
    private NFLTeam currentTeam;

}
