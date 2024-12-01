package interface_adapter.like;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Like use case.
 * Provides methods for interacting with the state.
 */
public class LikeViewModel extends ViewModel<LikeState> {
    public LikeViewModel() {
        super("like view");
        setState(new LikeState());
    }

//    private final LikeState state;
//
//    public LikeViewModel(LikeState state) {
//        this.state = state;
//    }
//
//    /**
//     * Gets the current user message.
//     *
//     * @return the user message
//     */
//    public String getUserMessage() {
//        return state.getUserMessage();
//    }
//
//    /**
//     * Updates the user message in the state.
//     *
//     * @param newMessage the new user message
//     */
//    public void updateUserMessage(String newMessage) {
//        state.setUserMessage(newMessage);
//    }
//
//    /**
//     * Adds a listener for changes in the state.
//     *
//     * @param listener the property change listener
//     */
//    public void addPropertyChangeListener(PropertyChangeListener listener) {
//        state.addPropertyChangeListener(listener);
//    }
//
//    /**
//     * Removes a listener for changes in the state.
//     *
//     * @param listener the property change listener
//     */
//    public void removePropertyChangeListener(PropertyChangeListener listener) {
//        state.removePropertyChangeListener(listener);
//    }
}
