package result;

/**
 * Holds data returned by calling fill
 */
public class FillResult {

    boolean success;
    String message;

    /**
     * Constructor
     * @param success
     * @param message
     */
    public FillResult(boolean success, String message) {
        this.success = success;
        this.message = message;
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
}
