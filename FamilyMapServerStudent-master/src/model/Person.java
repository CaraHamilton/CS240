package model;

/**
 * Holds the data for the person attributes
 */
public class Person {

    private String personID;
    private String associatedUsername;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;

    /**
     * Constructor
     * @param personID
     * @param associatedUsername
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName,
                  String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    /**
     * Set person ID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * Get person ID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Set associated email
     */
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    /**
     * Get associated email
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * Set first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set father ID
     */
    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    /**
     * Get father ID
     */
    public String getFatherID() {
        return fatherID;
    }

    /**
     * Set mother ID
     */
    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    /**
     * Get mother ID
     */
    public String getMotherID() {
        return motherID;
    }

    /**
     * Set spouse ID
     */
    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    /**
     * Get spouse ID
     */
    public String getSpouseID() {
        return spouseID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof Person) {
            Person oPerson = (Person) o;
            return oPerson.getPersonID().equals(getPersonID()) &&
                    oPerson.getAssociatedUsername().equals(getAssociatedUsername()) &&
                    oPerson.getFirstName().equals(getFirstName()) &&
                    oPerson.getLastName().equals(getLastName()) &&
                    oPerson.getGender().equals(getGender()) &&
                    oPerson.getFatherID().equals(getFatherID()) &&
                    oPerson.getMotherID().equals(getMotherID()) &&
                    oPerson.getSpouseID().equals(getSpouseID());
        } else {
            return false;
        }
    }
}
