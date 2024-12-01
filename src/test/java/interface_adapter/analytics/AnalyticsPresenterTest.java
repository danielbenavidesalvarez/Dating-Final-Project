package interface_adapter.analytics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.analytics.AnalyticsOutputData;

import static org.junit.jupiter.api.Assertions.*;

class AnalyticsPresenterTest {

    private AnalyticsViewModel viewModel;
    private AnalyticsPresenter presenter;

    @BeforeEach
    void setUp() {
        viewModel = new AnalyticsViewModel();
        presenter = new AnalyticsPresenter(viewModel);
    }

    @Test
    void testPresent() {
        // Prepare output data
        AnalyticsOutputData outputData = new AnalyticsOutputData(5, 10, 3, 2);

        // Call the present method
        presenter.present(outputData);

        // Assert that the ViewModel is updated correctly
        assertEquals(5, viewModel.getLikesGiven());
        assertEquals(10, viewModel.getLikesReceived());
        assertEquals(3, viewModel.getMatches());
        assertEquals(2, viewModel.getSharedInterests());
    }
}
