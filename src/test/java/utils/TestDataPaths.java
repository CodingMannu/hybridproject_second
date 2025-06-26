package utils;

/**
 * Utility class containing static paths for various test data JSON files.
 */
public class TestDataPaths {

    private static final String BASE_PATH = System.getProperty("user.dir") + "/src/test/resources/testdata/";

    public static final String LOGIN_PATH       = BASE_PATH + "Login.json";
    public static final String PROMO_PATH       = BASE_PATH + "Promo.json";
    public static final String RECHARGE_PATH    = BASE_PATH + "Recharge.json";
    public static final String GLOBAL_PATH      = BASE_PATH + "GlobalToastMessage.json";
    public static final String ASTROLOGER_PATH  = BASE_PATH + "Astrologer.json";
}
