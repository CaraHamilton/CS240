package model;

/**
 * Holds the data for the user attributes
 */
public class User {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;

    /**
     * Constructor
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     * @param personID
     */
    public User(String username, String password, String email, String firstName, String lastName,
                String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName= lastName;
        this.gender = gender;
        this.personID = personID;
    }

    /**
     * Set username
     */
    public void setUsername(String userName) { this.username = userName; }

    /**
     * Get username
     */
    public String getUsername() { return username; }

    /**
     * Set password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Get password
     */
    public String getPassword() { return password; }

    /**
     * Set email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Get email
     */
    public String getEmail() { return email; }

    /**
     * Set first name
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Get first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Set last name
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Get last name
     */
    public String getLastName() { return lastName; }

    /**
     * Set gender
     */
    public void setGender(String gender) { this.gender = gender; }

    /**
     * Get gender
     * @return
     */
    public String getGender() { return gender; }

    /**
     * Set person ID
     */
    public void setPersonID(String personID) { this.personID = personID; }

    /**
     * Get person ID
     */
    public String getPersonID() { return personID; }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof User) {
            User oUser = (User) o;
            return oUser.getUsername().equals(getUsername()) &&
                    oUser.getPassword().equals(getPassword()) &&
                    oUser.getEmail().equals(getEmail()) &&
                    oUser.getFirstName().equals(getFirstName()) &&
                    oUser.getLastName().equals(getLastName()) &&
                    oUser.getGender().equals(getGender()) &&
                    oUser.getPersonID().equals(getPersonID());
        } else {
            return false;
        }
    }
}
