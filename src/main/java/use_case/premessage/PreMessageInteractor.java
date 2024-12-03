package use_case.premessage;

import entity.User;

/**
 * The PreMessage Interactor.
 */
public class PreMessageInteractor implements PreMessageInputBoundary {
    private final PreMessageUserDataAccessInterface userDataAccessObject;
    private final PreMessageOutputBoundary preMessagePresenter;

    public PreMessageInteractor(PreMessageUserDataAccessInterface userDataAccessInterface,
                                PreMessageOutputBoundary preMessageOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.preMessagePresenter = preMessageOutputBoundary;
    }


    /**
     * Executes the pre-message use case.
     *
     * @param preMessageInputData the input data
     */
    @Override
    public void execute(PreMessageInputData preMessageInputData) {
        String username1 = preMessageInputData.getUsername1();
        String username2 = preMessageInputData.getUsername2();
        User user1 = userDataAccessObject.getUserByUsername(username1);
        User user2 = userDataAccessObject.getUserByUsername(username2);

        if (user1 == null || user2 == null) {
            preMessagePresenter.presentFailView("One or both users do not exist.");
            return;
        }

        if (user1.getLikedUsers().contains(username2) && user2.getLikedUsers().contains(username1)) {
            preMessagePresenter.presentSuccessView(preMessageInputData);
        } else {
            preMessagePresenter.presentFailView("Users have not mutually liked each other.");
        }
    }
}