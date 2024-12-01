package integration;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import interface_adapter.ViewManagerModel;
import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfilePresenter;
import interface_adapter.edit_profile.EditProfileState;
import interface_adapter.edit_profile.EditProfileViewModel;
import org.junit.jupiter.api.Test;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInteractor;
import view.EditProfileView;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EditProfileIntegrationTest {

    @Test
    void integrationTestEditProfileSuccess() {
        // Set up the user and data access
        CommonUser testUser = new CommonUser("user123", "password123"); // User ID and password
        testUser.setAge(25);
        testUser.setInterests("Reading");
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.save(testUser);

        // Set up the state, presenter, and interactor
        EditProfileState state = new EditProfileState();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        EditProfileViewModel viewModel = new EditProfileViewModel(state, viewManagerModel);
        EditProfilePresenter presenter = new EditProfilePresenter(state);
        EditProfileInputBoundary interactor = new EditProfileInteractor(presenter, dataAccessObject);

        // Create the controller and view
        EditProfileController controller = new EditProfileController(interactor);
        EditProfileView view = new EditProfileView(viewModel);
        view.setEditProfileController(controller);

        // Simulate user input
        JTextField userIdField = (JTextField) getComponentByName(view, "userIdField");
        JTextField nameField = (JTextField) getComponentByName(view, "nameField");
        JTextField ageField = (JTextField) getComponentByName(view, "ageField");
        JTextField interestsField = (JTextField) getComponentByName(view, "interestsField");
        JButton submitButton = (JButton) getComponentByName(view, "submitButton");

        userIdField.setText("user123"); // User ID
        nameField.setText("Updated User"); // New name
        ageField.setText("30"); // New age
        interestsField.setText("Music, Travel"); // New interests

        // Simulate button click
        submitButton.doClick();

        // Assert the updated user details
        JLabel resultLabel = (JLabel) getComponentByName(view, "resultLabel");
        assertEquals("Profile updated successfully", resultLabel.getText());

        // Validate updated user in data access object
        CommonUser updatedUser = (CommonUser) dataAccessObject.get("user123"); // Retrieve by userId
        assertNotNull(updatedUser);
        assertEquals("Updated User", updatedUser.getName()); // Validate updated name
        assertEquals(30, updatedUser.getAge()); // Validate updated age
        assertEquals("Music, Travel", updatedUser.getInterests()); // Validate updated interests
    }

    /**
     * Helper method to retrieve components by their name.
     */
    private Component getComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (name.equals(component.getName())) {
                return component;
            }
            if (component instanceof Container) {
                Component child = getComponentByName((Container) component, name);
                if (child != null) {
                    return child;
                }
            }
        }
        return null;
    }
    @Test
    void integrationTestEditProfileUsingSetters() {
        // Set up the user and data access
        CommonUser testUser = new CommonUser("user123", "password123");
        testUser.setAge(25);
        testUser.setInterests("Reading");
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        dataAccessObject.save(testUser);

        // Verify initial state
        assertEquals("user123", testUser.getName());
        assertEquals(25, testUser.getAge());
        assertEquals("Reading", testUser.getInterests());

        // Update user details using setters
        testUser.setName("Updated User");
        testUser.setAge(30);
        testUser.setInterests("Music, Travel");

        // Save updated user back to the data access object
        dataAccessObject.save(testUser);

        // Retrieve the updated user and verify details
        CommonUser updatedUser = (CommonUser) dataAccessObject.get("user123");
        assertNotNull(updatedUser);
        assertEquals("Updated User", updatedUser.getName());
        assertEquals(30, updatedUser.getAge());
        assertEquals("Music, Travel", updatedUser.getInterests());
    }

}
