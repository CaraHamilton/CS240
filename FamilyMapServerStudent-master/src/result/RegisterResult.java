package result;

/**
 * Holds data by registering a user
 */
public class RegisterResult {

    String authToken;
    String userName;
    String personID;
    String message;
    boolean success;

    /**
     * Constructor
     * @param authToken
     * @param userName
     * @param personID
     * @param message
     * @param success
     */
    public RegisterResult(String authToken, String userName, String personID, String message, boolean success) {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
        this.message = message;
        this.success = success;
    }

    public RegisterResult(String message, boolean success) {
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
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set PersonID
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Get PersonID
     * @return PersonID
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
    public boolean isSuccess() {
        return success;
    }
}
