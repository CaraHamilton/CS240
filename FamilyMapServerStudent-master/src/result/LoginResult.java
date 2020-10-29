package result;

/**
 * Holds data returned by logging in
 */
public class LoginResult {

    private String authToken;
    private String userName;
    private String personID;
    private String message;
    private boolean success;

    /**
     * Constructor
     * @param authToken
     * @param userName
     * @param personID
     * @param success
     */
    public LoginResult(String authToken, String userName, String personID, boolean success) {
        this.authToken = authToken;
        this.userName = userName;
        this. personID = personID;
        this.success = success;
    }

    public LoginResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    /**
     * Set authToken
     * @param authToken
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * Get authToken
     * @return authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Set userName
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get userName
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set personID
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Get personID
     * @return personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Set message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set success
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Get success
     * @return success
     */
    public boolean getSuccess() {
        return success;
    }
}
