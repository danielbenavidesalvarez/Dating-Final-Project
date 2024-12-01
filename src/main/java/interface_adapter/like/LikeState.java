package interface_adapter.like;

/**
 * The state class for the Like use case.
 * Responsible for maintaining and notifying changes in state.
 */
//public class LikeState {
//
//    private final PropertyChangeSupport support;
//    private String userMessage; // Message to be displayed in the view.
//
//    public LikeState() {
//        this.support = new PropertyChangeSupport(this);
//    }
//
//    /**
//     * Adds a listener for property changes.
//     *
//     * @param listener the property change listener
//     */
//    public void addPropertyChangeListener(PropertyChangeListener listener) {
//        support.addPropertyChangeListener(listener);
//    }
//
//    /**
//     * Removes a listener for property changes.
//     *
//     * @param listener the property change listener
//     */
//    public void removePropertyChangeListener(PropertyChangeListener listener) {
//        support.removePropertyChangeListener(listener);
//    }
//
//    /**
//     * Gets the current user message.
//     *
//     * @return the user message
//     */
//    public String getUserMessage() {
//        return userMessage;
//    }
//
//    /**
//     * Sets a new user message and notifies listeners.
//     *
//     * @param newMessage the new user message
//     */
//    public void setUserMessage(String newMessage) {
//        String oldMessage = this.userMessage;
//        this.userMessage = newMessage;
//        support.firePropertyChange("userMessage", oldMessage, newMessage);
//    }
//
//    /**
//     * Retrieves all registered property change listeners.
//     *
//     * @return an array of registered PropertyChangeListeners
//     */
//    public PropertyChangeListener[] getPropertyChangeListeners() {
//        return support.getPropertyChangeListeners();
//    }
//}

public class LikeState {
    private String likeruserID = "";

    private String likeduserID = "";

//    public LikeState(interface_adapter.change_password.LoggedInState copy) {
//        username = copy.username;
//        password = copy.password;
//        passwordError = copy.passwordError;
//    }

    // Because of the previous copy constructor, the default constructor must be explicit.
//    public LoggedInState() {
//
//    }

    public String getlikeruserID() {
        return likeruserID;
    }

    public void setlikeruserID(String likeruserID) {
        this.likeruserID = likeruserID;
    }

    public String getlikeduserID() {
        return likeduserID;
    }

    public void setlikeduserID(String likeduserID) {
        this.likeduserID = likeduserID;
    }
}
