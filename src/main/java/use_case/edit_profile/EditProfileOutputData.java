package use_case.edit_profile;

/**
 * Output data for the Edit Profile use case.
 */
public class EditProfileOutputData {
    private final boolean success;
    private final String message;

    public EditProfileOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
