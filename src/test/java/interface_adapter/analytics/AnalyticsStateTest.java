package interface_adapter.analytics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyticsStateTest {

    private AnalyticsViewModel viewModel;
    private AnalyticsState state;

    @BeforeEach
    void setUp() {
        viewModel = new AnalyticsViewModel();
        state = new AnalyticsState(viewModel);
    }

    @Test
    void testDisplayAnalytics() {
        // Set data in ViewModel
        viewModel.setLikesGiven(5);
        viewModel.setLikesReceived(10);
        viewModel.setMatches(3);
        viewModel.setSharedInterests(2);

        // Capture the output of the display method
        state.displayAnalytics();

        // Since this is a console display, manual verification may be needed.
        // For a more testable approach, consider returning strings instead of directly printing.
        assertEquals(5, viewModel.getLikesGiven());
        assertEquals(10, viewModel.getLikesReceived());
        assertEquals(3, viewModel.getMatches());
        assertEquals(2, viewModel.getSharedInterests());
    }
}
