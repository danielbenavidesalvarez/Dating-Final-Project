package interface_adapter.report_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.report_account.ReportAccountOutputData;

import static org.junit.jupiter.api.Assertions.*;

class ReportAccountPresenterTest {
    private ReportAccountPresenter presenter;
    private ReportAccountViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new ReportAccountViewModel();
        presenter = new ReportAccountPresenter(viewModel);
    }

    @Test
    void testPresentReportResult_Success() {
        // Arrange
        ReportAccountOutputData outputData = new ReportAccountOutputData(true, "Report submitted successfully.");

        // Act
        presenter.presentReportResult(outputData);

        // Assert
        assertTrue(viewModel.isSuccess());
        assertEquals("Report submitted successfully.", viewModel.getMessage());
    }

    @Test
    void testPresentReportResult_Failure() {
        // Arrange
        ReportAccountOutputData outputData = new ReportAccountOutputData(false, "Reported user does not exist.");

        // Act
        presenter.presentReportResult(outputData);

        // Assert
        assertFalse(viewModel.isSuccess());
        assertEquals("Reported user does not exist.", viewModel.getMessage());
    }
}
