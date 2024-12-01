package interface_adapter.edit_profile;

import use_case.edit_profile.EditProfileOutputBoundary;
import use_case.edit_profile.EditProfileOutputData;

/**
 * Presenter for the Edit Profile use case.
 * It processes the interactor's output and updates the state.
 */
public class EditProfilePresenter implements EditProfileOutputBoundary {
    private final EditProfileState state;

    public EditProfilePresenter(EditProfileState state) {
        this.state = state;
    }

    /**
     * Presents the output data to the state.
     *
     * @param outputData The output data from the interactor.
     */
    @Override
    public void present(EditProfileOutputData outputData) {
        // Update the state with the result
        state.setSuccess(outputData.isSuccess());
        state.setMessage(outputData.getMessage());
    }
}
