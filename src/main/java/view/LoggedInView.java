package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.ViewManagerModel;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private ViewManagerModel viewManagerModel; // For navigation between views

    private final JLabel username;
    private final JButton logOut;
    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;
    private final JButton editProfileButton;
    private final JButton analyticsButton;
    private final JButton reportAccountButton;
    private final JButton peopleButton;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();


        // Panel for buttons with FlowLayout to align them in a single row
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Add horizontal alignment and spacing

        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        editProfileButton = new JButton("Edit Profile"); // Initialize Edit Profile button
        buttons.add(editProfileButton);

        analyticsButton = new JButton("Analytics");
        buttons.add(analyticsButton);

        reportAccountButton = new JButton("Report Account");
        buttons.add(reportAccountButton); // Add button to the layout

        peopleButton = new JButton("People");
        buttons.add(peopleButton); // Add button to the layout


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                evt -> {
                    final LoggedInState currentState = loggedInViewModel.getState();
                    this.changePasswordController.execute(
                            currentState.getUsername(),
                            currentState.getPassword()
                    );
                }
        );

        logOut.addActionListener(
                evt -> {
                    final LoggedInState currentState = loggedInViewModel.getState();
                    this.logoutController.execute(currentState.getUsername());
                }
        );

        // Action listener for Edit Profile button
        editProfileButton.addActionListener(
                evt -> {
                    if (viewManagerModel != null) {
                        viewManagerModel.setState("EditProfileView"); // Navigate to Edit Profile View
                        viewManagerModel.firePropertyChanged();
                    }
                    else {
                        System.err.println("ViewManagerModel is not set! Cannot navigate to EditProfileView.");
                    }
                }
        );

        peopleButton.addActionListener(
                evt -> {
                    if (viewManagerModel != null) {
                        viewManagerModel.setState("people view"); // Navigate to Like View
                        viewManagerModel.firePropertyChanged();
                    }
                    else {
                        System.err.println("ViewManagerModel is not set! Cannot navigate to People View.");
                    }
                }
        );
        // Action listener for Analytics button
        analyticsButton.addActionListener(
                evt -> {
                    if (viewManagerModel != null) {
                        viewManagerModel.setState("AnalyticsView"); // Navigate to Analytics View
                        viewManagerModel.firePropertyChanged();
                    }
                    else {
                        System.err.println("ViewManagerModel is not set! Cannot navigate to AnalyticsView.");
                    }
                }
        );
        // Action listener for Report Account button
        reportAccountButton.addActionListener(evt -> {
            if (viewManagerModel != null) {
                viewManagerModel.setState("ReportAccountView"); // Navigate to Report Account View
                viewManagerModel.firePropertyChanged();
            }
            else {
                System.err.println("ViewManagerModel is not set! Cannot navigate to ReportAccountView.");
            }
        });
        // Add the button to the view
        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        } else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "Password updated for " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel; // Set ViewManagerModel for navigation
    }
}

