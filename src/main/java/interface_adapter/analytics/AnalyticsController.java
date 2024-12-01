package interface_adapter.analytics;

import use_case.analytics.AnalyticsInputBoundary;
import use_case.analytics.AnalyticsInputData;

public class AnalyticsController {
    private final AnalyticsInputBoundary interactor;

    public AnalyticsController(AnalyticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void executeAnalytics(String userId) {
        System.out.println("Controller: Executing analytics for user ID: " + userId); // Debugging
        AnalyticsInputData inputData = new AnalyticsInputData(userId);
        interactor.execute(inputData);
    }
}

