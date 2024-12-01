package use_case.analytics;

import entity.User;

public class AnalyticsInteractor implements AnalyticsInputBoundary {
    private final AnalyticsOutputBoundary presenter;
    private final AnalyticsUserDataAccessInterface userDataAccess;

    public AnalyticsInteractor(AnalyticsOutputBoundary presenter, AnalyticsUserDataAccessInterface userDataAccess) {
        this.presenter = presenter;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void execute(AnalyticsInputData inputData) {
        System.out.println("Interactor: Executing for user ID: " + inputData.getUserId());

        User user = userDataAccess.getUserById(inputData.getUserId());
        if (user == null) {
            System.out.println("Interactor: User not found.");
            throw new IllegalArgumentException("User not found.");
        }

        int likesGiven = userDataAccess.countLikesGiven(inputData.getUserId());
        int likesReceived = userDataAccess.countLikesReceived(inputData.getUserId());
        int matches = userDataAccess.countMatches(inputData.getUserId());
        int sharedInterests = userDataAccess.countSharedInterests(inputData.getUserId());

        System.out.println("Interactor: Likes Given = " + likesGiven);
        System.out.println("Interactor: Likes Received = " + likesReceived);
        System.out.println("Interactor: Matches = " + matches);
        System.out.println("Interactor: Shared Interests = " + sharedInterests);

        AnalyticsOutputData outputData = new AnalyticsOutputData(likesGiven, likesReceived, matches, sharedInterests);
        presenter.present(outputData);
    }
}

