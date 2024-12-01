package interface_adapter.like;

import use_case.like.LikeOutputBoundary;
import use_case.like.LikeOutputData;

/**
 * The presenter for the "Like" use case.
 */
//public class LikePresenter implements LikeOutputBoundary {
//
//    private String userMessage;
//    private LikeState likeState; // Link to the LikeState
//
//
//    /**
//     * Formats the output data into a user-friendly message.
//     *
//     * @param outputData the output data from the interactor
//     */
//    @Override
//    public void present(LikeOutputData outputData) {
//        // Format the user-friendly message
//        if (outputData.isSuccess()) {
//            userMessage = "Like action succeeded: " + outputData.getMessage();
//        } else {
//            userMessage = "Like action failed: " + outputData.getMessage();
//        }
//
////        // Update the state with the user message
////        if (likeState != null) {
////            likeState.setUserMessage(userMessage);
////        }
//    }
//
//    /**
//     * Retrieves the user-friendly message.
//     *
//     * @return the user message for the view
//     */
//    public String getUserMessage() {
//        return userMessage;
//    }
//
////    /**
////     * Links the presenter to the state.
////     *
////     * @param likeState the LikeState to update
////     */
//////    public void setState(LikeState likeState) {
//////        this.likeState = likeState;
//////    }
//
//    public boolean isSuccess() {
//        return userMessage != null;
//    }
//}
public class LikePresenter implements LikeOutputBoundary {

    private final LikeViewModel likeViewModel;

    public LikePresenter(LikeViewModel likeViewModel) {
        this.likeViewModel = likeViewModel;
    }

    @Override
    public void present(LikeOutputData outputData) {
        if (outputData.isMatch()) {
            likeViewModel.firePropertyChanged("Match");
        }
        else if (outputData.isSuccess()) {
            likeViewModel.firePropertyChanged("Liked");
        }
        else {
            likeViewModel.firePropertyChanged("Failed Like");
        }
    }


    }


//    public class void prepareSuccessView(LikeOutputData outputData) {
//        // currently there isn't anything to change based on the output data,
//        // since the output data only contains the username, which remains the same.
//        // We still fire the property changed event, but just to let the view know that
//        // it can alert the user that their password was changed successfully..
//        likeViewModel.firePropertyChanged("Liked Users");
//
//    }

////    @Override
//    public void prepareFailView(String error) {
//        // note: this use case currently can't fail
//    }
//}