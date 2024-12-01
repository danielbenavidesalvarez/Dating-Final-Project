
package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.like.LikeController;
import interface_adapter.like.LikeState;
import interface_adapter.like.LikeViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LikeView extends JPanel implements PropertyChangeListener {
    private final String viewName = "like view";
    private final LikeViewModel likeViewModel;
    private LikeController likeController;
//  private final LikeState likeState;
    private ViewManagerModel viewManagerModel; // Add ViewManagerModel for navigation

    private final JTextField likerUserIdField = new JTextField(15);
    private final JTextField likedUserIdField = new JTextField(15);
    private final JLabel resultLabel = new JLabel("");

    public LikeView(LikeViewModel likeViewModel) {

        this.likeViewModel = likeViewModel;
        this.likeViewModel.addPropertyChangeListener(this);
        // Register this view as a listener to the state
//        likeState.addPropertyChangeListener(this);

        // Set up the layout
        setLayout(new GridLayout(5, 2));

        // Add components
        add(new JLabel("Your User ID:"));
        add(likerUserIdField);

        add(new JLabel("User ID to Like:"));
        add(likedUserIdField);

        JButton likeButton = new JButton("Like");
        add(likeButton);
        add(resultLabel);

        JButton backButton = new JButton("Back"); // Add Back Button for navigation
        add(backButton);

        // Add action listener for the Like button
        likeButton.addActionListener(e -> handleLikeAction());

        // Add action listener for the Back button
        backButton.addActionListener(e -> handleBackAction());

        likerUserIdField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LikeState currentState = likeViewModel.getState();
                currentState.setlikeruserID(likerUserIdField.getText());
                likeViewModel.setState(currentState);
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

        likedUserIdField.getDocument().addDocumentListener(new DocumentListener() {

                private void documentListenerHelper() {
                    final LikeState currentState = likeViewModel.getState();
                    currentState.setlikeduserID(likedUserIdField.getText());
                    likeViewModel.setState(currentState);
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
    }

    void handleLikeAction() {
        LikeState statetopass = likeViewModel.getState();
        String locallikerUserId = statetopass.getlikeruserID();
        String locallikedUserId = statetopass.getlikeduserID();

        try {
            // Validation
            if (locallikerUserId == null || locallikerUserId.isEmpty()) {
                throw new IllegalArgumentException("Liker user ID cannot be null or empty.");
            }
            if (locallikedUserId == null || locallikedUserId.isEmpty()) {
                throw new IllegalArgumentException("Liked user ID cannot be null or empty.");
            }
//            if (likerUserId.equals(likedUserId)) {
//                throw new IllegalArgumentException("Users cannot like themselves.");
//            }

            // Delegate the "Like" action to the controller
//            final LikeState currentState = likeViewModel.getState();
//            this.changePasswordController.execute(
//                    currentState.getUsername(),
//                    currentState.getPassword()
//            );

            likeController.likeUser(locallikerUserId, locallikedUserId);

        } catch (IllegalArgumentException ex) {
            // Display validation errors directly in the view
            resultLabel.setText(ex.getMessage());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
//            final LikeState state = (LikeState) evt.getNewValue();
            resultLabel.setText((String) evt.getNewValue());
//            .setText(state.getUsername());
        } else if (evt.getPropertyName().equals("Match")) {
            final LikeState state = (LikeState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "MATCH! You can now message " + state.getlikeduserID() + " in Messages" );
        }
        else if (evt.getPropertyName().equals("Liked")) {
            final LikeState state = (LikeState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "You have successfully liked " + state.getlikeduserID());
            }
        else if (evt.getPropertyName().equals("Failed Like")) {
            JOptionPane.showMessageDialog(null, "Could not like "); //+ state.getlikeruserID());
            }



        }


    private void handleBackAction() {
        if (viewManagerModel != null) {
            viewManagerModel.setState("logged in"); // Navigate back to LoggedInView
            viewManagerModel.firePropertyChanged();
        }
    }

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        if ("userMessage".equals(evt.getPropertyName())) {
//            // Update the result label when the state changes
//            resultLabel.setText((String) evt.getNewValue());
//        }
//    }

    /**
     * Sets the ViewManagerModel for navigation.
     *
     * @param viewManagerModel the ViewManagerModel to set
     */
    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void setLikeController(LikeController likeController) {
        this.likeController = likeController;
    }

    public String getViewName() {
        return viewName;
    }
}
