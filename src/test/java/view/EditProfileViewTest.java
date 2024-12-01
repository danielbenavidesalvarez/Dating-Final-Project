package view;

import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfileViewModel;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.mockito.Mockito.*;

class EditProfileViewTest {

    @Test
    void testSubmitEditProfileWithValidInput() {
        EditProfileController mockController = mock(EditProfileController.class);
        EditProfileViewModel mockViewModel = mock(EditProfileViewModel.class);

        when(mockViewModel.getUserMessage()).thenReturn("Profile updated successfully");

        EditProfileView view = new EditProfileView(mockViewModel);
        view.setEditProfileController(mockController);

        JTextField userIdField = (JTextField) view.getComponent(1);
        JTextField nameField = (JTextField) view.getComponent(3);
        JTextField ageField = (JTextField) view.getComponent(5);
        JTextField interestsField = (JTextField) view.getComponent(7);

        userIdField.setText("testUser");
        nameField.setText("Test Name");
        ageField.setText("25");
        interestsField.setText("Coding, Reading");

        JButton submitButton = (JButton) view.getComponent(8);
        submitButton.doClick();

        verify(mockController).handleEditProfile("testUser", "Test Name", 25, "Coding, Reading");
    }

    @Test
    void testSubmitEditProfileWithInvalidAge() {
        EditProfileController mockController = mock(EditProfileController.class);
        EditProfileViewModel mockViewModel = mock(EditProfileViewModel.class);

        EditProfileView view = new EditProfileView(mockViewModel);
        view.setEditProfileController(mockController);

        JTextField userIdField = (JTextField) view.getComponent(1);
        JTextField nameField = (JTextField) view.getComponent(3);
        JTextField ageField = (JTextField) view.getComponent(5);
        JTextField interestsField = (JTextField) view.getComponent(7);

        userIdField.setText("testUser");
        nameField.setText("Test Name");
        ageField.setText("-5");
        interestsField.setText("Coding, Reading");

        JButton submitButton = (JButton) view.getComponent(8);
        submitButton.doClick();

        verify(mockController, never()).handleEditProfile(anyString(), anyString(), anyInt(), anyString());

        JLabel resultLabel = (JLabel) view.getComponent(9);
        assert resultLabel.getText().equals("Validation failed: Age must be positive");
    }
}
