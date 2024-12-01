package interface_adapter.edit_profile;

import interface_adapter.edit_profile.EditProfilePresenter;
import interface_adapter.edit_profile.EditProfileState;
import org.junit.jupiter.api.Test;
import use_case.edit_profile.EditProfileOutputData;

import static org.junit.jupiter.api.Assertions.*;

class EditProfilePresenterTest {

    @Test
    void testPresentSuccess() {
        EditProfileState state = new EditProfileState();
        EditProfilePresenter presenter = new EditProfilePresenter(state);

        EditProfileOutputData outputData = new EditProfileOutputData(true, "Profile updated successfully");
        presenter.present(outputData);

        assertTrue(state.isSuccess());
        assertEquals("Profile updated successfully", state.getMessage());
    }

    @Test
    void testPresentFailure() {
        EditProfileState state = new EditProfileState();
        EditProfilePresenter presenter = new EditProfilePresenter(state);

        EditProfileOutputData outputData = new EditProfileOutputData(false, "Invalid input: Name cannot be empty");
        presenter.present(outputData);

        assertFalse(state.isSuccess());
        assertEquals("Invalid input: Name cannot be empty", state.getMessage());
    }
}
