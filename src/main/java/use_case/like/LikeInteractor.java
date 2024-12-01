package use_case.like;

import entity.User;

/**
 * Interactor for the Like User use case.
 */
public class LikeInteractor implements LikeInputBoundary {
    private final LikeOutputBoundary likepresenter;
    private final LikeUserDataAccessInterface dataAccess;

    public LikeInteractor(LikeOutputBoundary likepresenter, LikeUserDataAccessInterface dataAccess) {
        this.likepresenter = likepresenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(LikeInputData inputData) {
        // Step 1: Retrieve the liking user
        User user = dataAccess.findById(inputData.getUserId());
        if (user == null) {
            likepresenter.present(new LikeOutputData(false, "User not found", false));
            return;
        }

        // Step 2: Retrieve the liked user
        User likedUser = dataAccess.findById(inputData.getLikedUserId());
        if (likedUser == null) {
            likepresenter.present(new LikeOutputData(false, "Liked user not found", false));
            return;
        }

        // Step 3: Validate the like operation
//        if (user.getUserId().equals(likedUser.getUserId())) {
//            likepresenter.present(new LikeOutputData(false, "Users cannot like themselves"));
//            return;
//        }

        if (((entity.CommonUser) user).hasLiked(likedUser.getUserId())) {
            likepresenter.present(new LikeOutputData(false, "User already liked this person", false));
            return;
        }

        // Step 4: Perform the like operation
        try {
            if (likedUser.getLikedUsers().contains(user.getUserId())) {
                user.likeUser(likedUser);
                dataAccess.save(user); // Save the updated user to persist the like
                likepresenter.present(new LikeOutputData(true, "Match", true));
            }
            else {
                user.likeUser(likedUser);
                dataAccess.save(user);
                likepresenter.present(new LikeOutputData(true, "User liked", false));
            }

        }
        catch (Exception e) {
            likepresenter.present(new LikeOutputData(false, e.getMessage(), false));
        }
    }
}
