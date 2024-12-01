package interface_adapter.analytics;

import use_case.analytics.AnalyticsOutputBoundary;
import use_case.analytics.AnalyticsOutputData;

public class AnalyticsPresenter implements AnalyticsOutputBoundary {
    private final AnalyticsViewModel viewModel;

    public AnalyticsPresenter(AnalyticsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(AnalyticsOutputData outputData) {
        System.out.println("Presenting data: "); // Debugging
        System.out.println("Likes Given: " + outputData.getLikesGiven());
        System.out.println("Likes Received: " + outputData.getLikesReceived());
        System.out.println("Matches: " + outputData.getMatches());
        System.out.println("Shared Interests: " + outputData.getSharedInterests());
        viewModel.setLikesGiven(outputData.getLikesGiven());
        viewModel.setLikesReceived(outputData.getLikesReceived());
        viewModel.setMatches(outputData.getMatches());
        viewModel.setSharedInterests(outputData.getSharedInterests());
    }
}
