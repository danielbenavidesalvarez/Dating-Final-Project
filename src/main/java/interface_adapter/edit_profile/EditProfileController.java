package interface_adapter.edit_profile;

import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInputData;

/**
 * Controller for the Edit Profile use case.
 * It bridges the UI layer with the interactor.
 */
public class EditProfileController {
    private final EditProfileInputBoundary interactor;

    public EditProfileController(EditProfileInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the request to edit a user's profile.
     *
     * @param userId    The user's ID.
     * @param name      The new name of the user.
     * @param age       The new age of the user.
     * @param interests The new interests of the user.
     */
    public void handleEditProfile(String userId, String name, int age, String interests) {
        EditProfileInputData inputData = new EditProfileInputData(userId, name, age, interests);
        interactor.execute(inputData);
    }
}
