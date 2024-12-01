package use_case.edit_profile;

import entity.User;

/**
 * Interactor for the Edit Profile use case.
 */
public class EditProfileInteractor implements EditProfileInputBoundary {
    private final EditProfileOutputBoundary outputBoundary;
    private final UserDataAccessInterface dataAccess;

    public EditProfileInteractor(EditProfileOutputBoundary outputBoundary, UserDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(EditProfileInputData inputData) {
        // Step 1: Validate input
        String validationError = validateInput(inputData);
        if (validationError != null) {
            outputBoundary.present(new EditProfileOutputData(false, validationError));
            return;
        }

        // Step 2: Retrieve the user
        User user = dataAccess.findById(inputData.getUserId());
        if (user == null) {
            outputBoundary.present(new EditProfileOutputData(false, "User not found"));
            return;
        }

        // Step 3: Update user fields
        user.setName(inputData.getName());
        user.setAge(inputData.getAge());
        user.setInterests(inputData.getInterests());
        user.setUserId(inputData.getUserId());

        // Step 4: Save the updated user
        dataAccess.save(user);

        // Step 5: Return success
        outputBoundary.present(new EditProfileOutputData(true, "Profile updated successfully"));
    }

    private String validateInput(EditProfileInputData inputData) {
        if (inputData.getName() == null || inputData.getName().isEmpty()) {
            return "Invalid input: Name cannot be empty";
        }
        if (inputData.getAge() <= 0) {
            return "Invalid input: Age must be a positive number";
        }
        if (inputData.getInterests() == null || inputData.getInterests().isEmpty()) {
            return "Invalid input: Interests cannot be empty";
        }
        if (inputData.getUserId() == null || inputData.getUserId().isEmpty()) {
            return "Invalid input: UserId cannot be empty";
        }
        return null; // No validation errors
    }
}
