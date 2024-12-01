package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.report_account.ReportAccountController;
import interface_adapter.report_account.ReportAccountViewModel;

import javax.swing.*;
import java.awt.*;

public class ReportAccountView extends JPanel {
    private final ReportAccountController controller;
    private final ReportAccountViewModel viewModel;
    private ViewManagerModel viewManagerModel; // For navigation

    public ReportAccountView(ReportAccountController controller, ReportAccountViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Report Account");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        // Input for User ID
        JLabel userIdLabel = new JLabel("User ID to report:");
        JTextField userIdField = new JTextField(15);
        this.add(userIdLabel);
        this.add(userIdField);

        // Issue Type Dropdown
        JLabel issueLabel = new JLabel("Select issue type:");
        String[] issueTypes = {
                "Inappropriate Language/Profanity & Adult Content",
                "Bullying/Harassment/Discrimination",
                "Account Theft",
                "Scamming",
                "Real Life Threat/Suicide Threat"
        };
        JComboBox<String> issueDropdown = new JComboBox<>(issueTypes);
        this.add(issueLabel);
        this.add(issueDropdown);

        // Description Area
        JLabel descriptionLabel = new JLabel("Detailed description:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        this.add(descriptionLabel);
        this.add(scrollPane);

        // Submit Button
        JButton submitButton = new JButton("Submit Report");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(submitButton);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(backButton);

        // Add Action Listeners
        submitButton.addActionListener(e -> {
            String userId = userIdField.getText().trim();
            String issueType = (String) issueDropdown.getSelectedItem();
            String description = descriptionArea.getText().trim();

            controller.reportAccount(userId, issueType, description);
        });

        backButton.addActionListener(e -> navigateToLoggedInView());

        // Listen for ViewModel updates
        viewModel.addObserver(evt -> {
            if ("update".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(
                        this,
                        viewModel.getMessage(),
                        viewModel.isSuccess() ? "Success" : "Error",
                        viewModel.isSuccess() ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    private void navigateToLoggedInView() {
        if (viewManagerModel != null) {
            viewManagerModel.setState("logged in");
            viewManagerModel.firePropertyChanged();
        }
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }
}
