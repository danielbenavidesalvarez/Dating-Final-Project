package use_case.analytics;

public class AnalyticsInputData {
    private final String userId;

    public AnalyticsInputData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}

