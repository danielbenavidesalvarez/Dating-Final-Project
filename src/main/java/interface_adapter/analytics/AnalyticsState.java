package interface_adapter.analytics;

public class AnalyticsState {
    private final AnalyticsViewModel viewModel;

    public AnalyticsState(AnalyticsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public int getLikesGiven() {
        return viewModel.getLikesGiven();
    }

    public int getLikesReceived() {
        return viewModel.getLikesReceived();
    }

    public int getMatches() {
        return viewModel.getMatches();
    }

    public int getSharedInterests() {
        return viewModel.getSharedInterests();
    }

    public void displayAnalytics() {
        System.out.println("Likes Given: " + viewModel.getLikesGiven());
        System.out.println("Likes Received: " + viewModel.getLikesReceived());
        System.out.println("Matches: " + viewModel.getMatches());
        System.out.println("Shared Interests: " + viewModel.getSharedInterests());
    }
}
