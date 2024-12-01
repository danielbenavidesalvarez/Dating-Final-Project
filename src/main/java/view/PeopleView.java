package view;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.people.PeopleController;
import interface_adapter.people.PeopleViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class PeopleView extends JPanel implements PropertyChangeListener {
    private final String viewName = "people view";
    private final PeopleViewModel peopleViewModel;
    private PeopleController peopleController;
    //  private final LikeState likeState;
    private ViewManagerModel viewManagerModel; // Add ViewManagerModel for navigation
    private final JButton likeButton;

    private final JLabel resultLabel = new JLabel("");

    public PeopleView(PeopleViewModel peopleViewModel) {

        this.peopleViewModel = peopleViewModel;
        this.peopleViewModel.addPropertyChangeListener(this);
        // Register this view as a listener to the state
//        likeState.addPropertyChangeListener(this);

        // Set up the layout
        setLayout(new GridLayout(5, 2));

//        // Add components
//        add(new JLabel("Your User ID:"));
//        add(likerUserIdField);
//
//        add(new JLabel("User ID to Like:"));
//        add(likedUserIdField);
        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton showpeopleButton = new JButton("Show People");
        add(showpeopleButton);
        add(resultLabel);

        likeButton = new JButton("Likes"); // Initialize the Like button
        buttons.add(likeButton);

        JButton backButton = new JButton("Back"); // Add Back Button for navigation
        buttons.add(backButton);

        // Add action listener for the Like button
        showpeopleButton.addActionListener(e -> handleshowpeoplebuttonAction());

//        // Add action listener for the Back button
        backButton.addActionListener(e -> handleBackAction());

        likeButton.addActionListener(
                evt -> {
                    if (viewManagerModel != null) {
                        viewManagerModel.setState("like view"); // Navigate to Like View
                        viewManagerModel.firePropertyChanged();
                    }
                    else {
                        System.err.println("ViewManagerModel is not set! Cannot navigate to LikeView.");
                    }
                }
        );
        this.add(buttons);
    }

    void handleshowpeoplebuttonAction() {

        try {
            peopleController.showpeople();

        } catch (IllegalArgumentException ex) {
            // Display validation errors directly in the view
            resultLabel.setText(ex.getMessage());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getPropertyName().equals("state")) {
////            final LikeState state = (LikeState) evt.getNewValue();
//            resultLabel.setText((String) evt.getNewValue());
////            .setText(state.getUsername());
//        }
        if (evt.getPropertyName().equals("Show People")) {
//            final PeopleState state = (PeopleState) evt.getNewValue();
            for (User person: peopleViewModel.getState().getPeople()) {
//                add(personbutton);
                JOptionPane.showMessageDialog(
                        null,
                        "" + person.getName() + " is " + person.getAge() + " years old. "
                + person.getName() + " likes " + person.getInterests() + ". Their username is " +
                        person.getUserId() + ". Like them now!");
            }

            // JOptionPane.showMessageDialog(null, "MATCH! You can now message " + state.getlikeduserID() + " in Messages" );
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

    public void setPeopleController(PeopleController peopleController) {
        this.peopleController = peopleController;
    }

    public String getViewName() {
        return viewName;
    }
}