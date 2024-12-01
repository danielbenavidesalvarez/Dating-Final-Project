package use_case.report_account;

import data_access.InMemoryReportAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReportAccountInteractorTest {
    private ReportAccountInteractor interactor;
    private MockReportAccountOutputBoundary outputBoundary;
    private InMemoryReportAccountRepository repository;
    private MockReportAccountUserDataAccess userDataAccess;

    @BeforeEach
    void setUp() {
        outputBoundary = new MockReportAccountOutputBoundary();
        repository = new InMemoryReportAccountRepository();
        userDataAccess = new MockReportAccountUserDataAccess();
        interactor = new ReportAccountInteractor(outputBoundary, repository, userDataAccess);
    }

    @Test
    void testSuccessfulReport() {
        // Arrange
        userDataAccess.addUser("user123");
        ReportAccountInputData inputData = new ReportAccountInputData(
                "user123",
                "Bullying/Harassment",
                "This user is sending offensive messages."
        );

        // Act
        interactor.reportAccount(inputData);

        // Assert
        assertTrue(outputBoundary.isSuccess());
        assertEquals("Report submitted successfully.", outputBoundary.getMessage());
        assertEquals(1, repository.getAllReports().size());
    }

    @Test
    void testUserDoesNotExist() {
        // Arrange
        ReportAccountInputData inputData = new ReportAccountInputData(
                "nonexistent_user",
                "Bullying/Harassment",
                "This user is sending offensive messages."
        );

        // Act
        interactor.reportAccount(inputData);

        // Assert
        assertFalse(outputBoundary.isSuccess());
        assertEquals("Reported user does not exist.", outputBoundary.getMessage());
        assertEquals(0, repository.getAllReports().size());
    }

    @Test
    void testEmptyUserId() {
        // Arrange
        ReportAccountInputData inputData = new ReportAccountInputData(
                "",
                "Bullying/Harassment",
                "This user is sending offensive messages."
        );

        // Act
        interactor.reportAccount(inputData);

        // Assert
        assertFalse(outputBoundary.isSuccess());
        assertEquals("User ID cannot be empty.", outputBoundary.getMessage());
        assertEquals(0, repository.getAllReports().size());
    }

    @Test
    void testEmptyIssueType() {
        // Arrange
        userDataAccess.addUser("user123");
        ReportAccountInputData inputData = new ReportAccountInputData(
                "user123",
                "",
                "This user is sending offensive messages."
        );

        // Act
        interactor.reportAccount(inputData);

        // Assert
        assertFalse(outputBoundary.isSuccess());
        assertEquals("Issue type cannot be empty.", outputBoundary.getMessage());
        assertEquals(0, repository.getAllReports().size());
    }

    @Test
    void testEmptyDescription() {
        // Arrange
        userDataAccess.addUser("user123");
        ReportAccountInputData inputData = new ReportAccountInputData(
                "user123",
                "Bullying/Harassment",
                ""
        );

        // Act
        interactor.reportAccount(inputData);

        // Assert
        assertFalse(outputBoundary.isSuccess());
        assertEquals("Description cannot be empty.", outputBoundary.getMessage());
        assertEquals(0, repository.getAllReports().size());
    }

    @Test
    void testRepositoryFailsToSave() {
        // Arrange
        userDataAccess.addUser("user123");
        repository.setFailOnSave(true); // Simulate a repository save failure
        ReportAccountInputData inputData = new ReportAccountInputData(
                "user123",
                "Bullying/Harassment",
                "This user is sending offensive messages."
        );

        // Act
        interactor.reportAccount(inputData);

        // Assert
        assertFalse(outputBoundary.isSuccess());
        assertEquals("Failed to submit the report.", outputBoundary.getMessage());
        assertEquals(0, repository.getAllReports().size());
    }

    // Mock Output Boundary
    private static class MockReportAccountOutputBoundary implements ReportAccountOutputBoundary {
        private boolean success;
        private String message;

        @Override
        public void presentReportResult(ReportAccountOutputData outputData) {
            this.success = outputData.isSuccess();
            this.message = outputData.getMessage();
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }

    // Mock User Data Access
    private static class MockReportAccountUserDataAccess implements ReportAccountUserDataAccessInterface {
        private final Set<String> userIds = new HashSet<>();

        @Override
        public boolean doesUserExist(String userId) {
            return userIds.contains(userId);
        }

        public void addUser(String userId) {
            userIds.add(userId);
        }
    }
}
