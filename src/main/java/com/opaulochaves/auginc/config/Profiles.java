package com.opaulochaves.auginc.config;

/**
 * This class defines the Spring profiles used in the project. The idea behind
 * this class is that it helps us to avoid typos when we are using these
 * profiles. At the moment there are two profiles which are described in the
 * following:
 * <ul>
 * <li>The DEVELOPEMNT profile is used when we run our application in development mode.</li>
 * <li>The PRODUCTION profile is used when we run our application in production mode.</li>
 * <li>The GRANULAR_SECURITY profile is used when granular-security is on.</li>
 * </ul>
 *
 * @author Petri Kainulainen
 */
public class Profiles {

    public static final String DEVELOPMENT = "development";
    public static final String PRODUCTION = "production";
    public static final String GRANULAR_SECURITY = "granular-security";

    /**
     * Prevent instantiation.
     */
    private Profiles() {
    }
}
