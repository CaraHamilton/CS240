package request;

/**
 * Holds the data passed in by user to register
 */
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;

    /**
     * Constructor
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName,
                           String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName= lastName;
        this.gender = gender;
    }

    /**
     * Set username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set password
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

    /**
     * Set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get gender
     * @return gender
     */
    public String getGender() {
        return gender;
    }
}
