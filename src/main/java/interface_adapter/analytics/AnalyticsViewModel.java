package interface_adapter.analytics;

public class AnalyticsViewModel {
    private int likesGiven;
    private int likesReceived;
    private int matches;
    private int sharedInterests;

    // Getters and setters
    public int getLikesGiven() {
        return likesGiven;
    }

    public void setLikesGiven(int likesGiven) {
        this.likesGiven = likesGiven;
    }

    public int getLikesReceived() {
        return likesReceived;
    }

    public void setLikesReceived(int likesReceived) {
        this.likesReceived = likesReceived;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getSharedInterests() {
        return sharedInterests;
    }

    public void setSharedInterests(int sharedInterests) {
        this.sharedInterests = sharedInterests;
    }
}
