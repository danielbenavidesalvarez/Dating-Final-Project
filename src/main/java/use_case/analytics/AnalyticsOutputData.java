package use_case.analytics;

public class AnalyticsOutputData {
    private final int likesGiven;
    private final int likesReceived;
    private final int matches;
    private final int sharedInterests;

    public AnalyticsOutputData(int likesGiven, int likesReceived, int matches, int sharedInterests) {
        this.likesGiven = likesGiven;
        this.likesReceived = likesReceived;
        this.matches = matches;
        this.sharedInterests = sharedInterests;
    }

    public int getLikesGiven() {
        return likesGiven;
    }

    public int getLikesReceived() {
        return likesReceived;
    }

    public int getMatches() {
        return matches;
    }

    public int getSharedInterests() {
        return sharedInterests;
    }
}
