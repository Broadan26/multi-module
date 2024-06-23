package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Model for an NFL Team
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NFLTeam {

    private Long teamId;
    private String name;
    private String cityLocation;
    private String stateLocation;
    private String stadiumName;

}
