package util;

/**
 * List of pre-defined player positions
 */
public class PlayerPosition {

    private PlayerPosition() {
        throw new UnsupportedOperationException("This class cannot be constructed.");
    }

    // Offense
    public static final String QUARTERBACK = "QB";
    public static final String HALFBACK = "HB";
    public static final String FULLBACK = "FB";
    public static final String WIDE_RECEIVER = "WR";
    public static final String TIGHT_END = "TE";
    public static final String LEFT_TACKLE = "LT";
    public static final String LEFT_GUARD = "LG";
    public static final String CENTER = "OC";
    public static final String RIGHT_GUARD = "RG";
    public static final String RIGHT_TACKLE = "RT";

    // Defense
    public static final String DEFENSIVE_END = "DE";
    public static final String DEFENSIVE_TACKLE = "DT";
    public static final String NOSE_TACKLE = "NT";
    public static final String LINEBACKER = "LB";
    public static final String CORNERBACK = "CB";
    public static final String STRONG_SAFETY = "SS";
    public static final String FREE_SAFETY = "FS";

    // Special Teams
    public static final String LONG_SNAPPER = "LS";
    public static final String PLACEKICKER = "PK";
    public static final String PUNTER = "PT";

}
