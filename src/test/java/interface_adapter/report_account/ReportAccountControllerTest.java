package interface_adapter.report_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.report_account.ReportAccountInputBoundary;
import use_case.report_account.ReportAccountInputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportAccountControllerTest {
    private ReportAccountController controller;
    private ReportAccountInputBoundary mockInteractor;

    @BeforeEach
    void setUp() {
        mockInteractor = mock(ReportAccountInputBoundary.class);
        controller = new ReportAccountController(mockInteractor);
    }

    @Test
    void testReportAccount() {
        // Arrange
        String reportedUserId = "user123";
        String issueType = "Bullying/Harassment";
        String description = "This user is sending offensive messages.";

        // Act
        controller.reportAccount(reportedUserId, issueType, description);

        // Assert
        ArgumentCaptor<ReportAccountInputData> captor = ArgumentCaptor.forClass(ReportAccountInputData.class);
        verify(mockInteractor, times(1)).reportAccount(captor.capture());

        ReportAccountInputData capturedData = captor.getValue();
        assertEquals(reportedUserId, capturedData.getReportedUserId());
        assertEquals(issueType, capturedData.getIssueType());
        assertEquals(description, capturedData.getDescription());
    }
}
