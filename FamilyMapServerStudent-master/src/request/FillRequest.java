package request;

/**
 * Holds data from user to fill tables
 */
public class FillRequest {

    private String userName;
    private int generations;

    /**
     * Constructor
     * @param userName
     * @param generations
     */
    public FillRequest(String userName, int generations) {
        this.userName = userName;
        this.generations = generations;
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
     * Set generations
     * @param generations
     */
    public void setGenerations(int generations) {
        this.generations = generations;
    }

    /**
     * Get generations
     * @return
     */
    public int getGenerations() {
        return generations;
    }
}
