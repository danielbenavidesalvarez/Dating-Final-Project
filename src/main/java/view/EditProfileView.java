package view;

import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfileView extends JPanel {
    private final EditProfileViewModel viewModel;
    private EditProfileController controller;

    private final JTextField userIdField = new JTextField(15);
    private final JTextField nameField = new JTextField(15);
    private final JTextField ageField = new JTextField(5);
    private final JTextField interestsField = new JTextField(20);
    private final JLabel resultLabel = new JLabel();

    public EditProfileView(EditProfileViewModel viewModel) {
        this.viewModel = viewModel;

        // Set up the layout
        setLayout(new GridLayout(6, 2));

        userIdField.setName("userIdField");
        nameField.setName("nameField");
        ageField.setName("ageField");
        interestsField.setName("interestsField");
        resultLabel.setName("resultLabel");

        add(new JLabel("User ID:"));
        add(userIdField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Interests:"));
        add(interestsField);

        JButton submitButton = new JButton("Update Profile");
        submitButton.setName("submitButton");
        add(submitButton);
        add(resultLabel);

        // Handle button click
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String name = nameField.getText();
                String ageText = ageField.getText();
                String interests = interestsField.getText();

                // Validate inputs
                if (userId.isEmpty() || name.isEmpty() || interests.isEmpty()) {
                    resultLabel.setText("Validation failed: Fields cannot be empty");
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageText);
                    if (age <= 0) {
                        resultLabel.setText("Validation failed: Age must be positive");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    resultLabel.setText("Validation failed: Age must be a number");
                    return;
                }

                // Delegate to the controller
                controller.handleEditProfile(userId, name, age, interests);

                // Update the result label
                resultLabel.setText(viewModel.getUserMessage());
            }
        });
        JButton backButton = new JButton("Back");
        backButton.setName("backButton");
        add(backButton);

        backButton.addActionListener(e -> {
            System.out.println("Back button clicked. Navigating to LoggedInView.");
            viewModel.getViewManagerModel().setState("logged in"); // Update state to LoggedInView
            viewModel.getViewManagerModel().firePropertyChanged(); // Notify the change
        });

    }

    public void setEditProfileController(EditProfileController controller) {
        this.controller = controller;
    }

    public String getViewName() {
        return "EditProfileView";
    }
}
