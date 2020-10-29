package model;

import java.sql.Time;

/**
 * Holds the data for the authTokens attributes
 */
public class AuthToken {

    private String authToken;
    private String userID;

    /**
     * Constructor
     * @param authToken
     * @param userID
     */
    public AuthToken(String authToken, String userID) {
        this.authToken = authToken;
        this.userID = userID;
    }

    /**
     * Set auth token
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * Get auth token
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Set user ID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Get user ID
     */
    public String getUserID() {
        return userID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof AuthToken) {
            AuthToken oAuthToken = (AuthToken) o;
            return oAuthToken.getAuthToken().equals(getAuthToken()) &&
                    oAuthToken.getUserID().equals(getUserID());
        } else {
            return false;
        }
    }
}