package database;

import utilities.Property;

final class ConnectInfoDto {

    private static final String url;
    private static final String userId;
    private static final String password;

    static {
        url            = Property.getDatabaseProperty("url");
        userId      = Property.getDatabaseProperty("userId");
        password = Property.getDatabaseProperty("password");
    }

    private ConnectInfoDto() {
    }

    protected static String getUrl() {
        return url;
    }

    protected static String getUserId() {
        return userId;
    }

    protected static String getPassword() {
        return password;
    }

}
