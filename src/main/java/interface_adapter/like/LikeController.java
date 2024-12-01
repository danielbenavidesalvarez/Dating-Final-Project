package interface_adapter.like;

import use_case.like.LikeInputBoundary;
import use_case.like.LikeInputData;

/**
 * The controller for the "Like" use case.
 * Responsible for handling user input and delegating the request to the interactor.
 */
public class LikeController {

    private final LikeInputBoundary likeinteractor;

    /**
     * Constructor for the LikeController.
     *
     * @param likeInputBoundary the input boundary for the Like use case
     */
    public LikeController(LikeInputBoundary likeInputBoundary) {
        this.likeinteractor = likeInputBoundary;
    }

    /**
     * Handles the action of liking a user.
     *
     * @param likerUserId the ID of the user performing the like action
     * @param likedUserId the ID of the user being liked
     */
    public void likeUser(String likerUserId, String likedUserId) {
        if (likerUserId == null || likerUserId.isEmpty()) {
            throw new IllegalArgumentException("Liker user ID cannot be null or empty.");
        }
        if (likedUserId == null || likedUserId.isEmpty()) {
            throw new IllegalArgumentException("Liked user ID cannot be null or empty.");
        }
//        if (likerUserId.equals(likedUserId)) {
//            throw new IllegalArgumentException("Users cannot like themselves.");
//        }

        // Create input data and delegate the request to the interactor
        LikeInputData inputData = new LikeInputData(likerUserId, likedUserId);
        likeinteractor.execute(inputData);
    }
}
