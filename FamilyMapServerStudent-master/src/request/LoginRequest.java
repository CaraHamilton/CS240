package request;

/**
 * Holds data passed in by user to login
 */
public class LoginRequest {

    private String userName;
    private String password;

    /**
     * Constructor
     * @param userName
     * @param password
     */
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Set username
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get username
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get password
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
