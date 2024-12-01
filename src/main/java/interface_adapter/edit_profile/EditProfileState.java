package interface_adapter.edit_profile;

/**
 * State for the Edit Profile use case.
 * It holds the current success status and message.
 */
public class EditProfileState {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
