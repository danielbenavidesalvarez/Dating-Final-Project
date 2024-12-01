package interface_adapter.edit_profile;

import interface_adapter.edit_profile.EditProfileState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditProfileStateTest {

    @Test
    void testStateInitialization() {
        EditProfileState state = new EditProfileState();

        assertFalse(state.isSuccess());
        assertNull(state.getMessage());
    }

    @Test
    void testSettersAndGetters() {
        EditProfileState state = new EditProfileState();

        state.setSuccess(true);
        state.setMessage("Profile updated successfully");

        assertTrue(state.isSuccess());
        assertEquals("Profile updated successfully", state.getMessage());
    }
}
