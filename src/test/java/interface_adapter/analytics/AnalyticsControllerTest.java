package interface_adapter.analytics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.analytics.AnalyticsInputBoundary;
import use_case.analytics.AnalyticsInputData;

import static org.mockito.Mockito.*;

class AnalyticsControllerTest {

    private AnalyticsInputBoundary interactor;
    private AnalyticsController controller;

    @BeforeEach
    void setUp() {
        interactor = mock(AnalyticsInputBoundary.class);
        controller = new AnalyticsController(interactor);
    }

    @Test
    void testExecuteAnalytics() {
        String userId = "user123";

        // Call the method to be tested
        controller.executeAnalytics(userId);

        // Verify the interactor's execute method was called with the correct data
        verify(interactor, times(1)).execute(argThat(inputData ->
                inputData.getUserId().equals(userId)
        ));
    }
}
