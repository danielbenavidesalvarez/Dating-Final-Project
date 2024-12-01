package use_case.report_account;

import data_access.ReportAccountRepository;

public class ReportAccountInteractor implements ReportAccountInputBoundary {
    private final ReportAccountOutputBoundary outputBoundary;
    private final ReportAccountRepository repository;
    private final ReportAccountUserDataAccessInterface userDataAccess;

    public ReportAccountInteractor(
            ReportAccountOutputBoundary outputBoundary,
            ReportAccountRepository repository,
            ReportAccountUserDataAccessInterface userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.repository = repository;
        this.userDataAccess = userDataAccess; // Correctly initializing userDataAccess
    }

    @Override
    public void reportAccount(ReportAccountInputData inputData) {
        String reportedUserId = inputData.getReportedUserId();
        String issueType = inputData.getIssueType();
        String description = inputData.getDescription();

        // Validate input
        if (reportedUserId == null || reportedUserId.trim().isEmpty()) {
            outputBoundary.presentReportResult(new ReportAccountOutputData(false, "User ID cannot be empty."));
            return;
        }
        if (!userDataAccess.doesUserExist(reportedUserId)) {
            outputBoundary.presentReportResult(new ReportAccountOutputData(false, "Reported user does not exist."));
            return;
        }
        if (issueType == null || issueType.trim().isEmpty()) {
            outputBoundary.presentReportResult(new ReportAccountOutputData(false, "Issue type cannot be empty."));
            return;
        }
        if (description == null || description.trim().isEmpty()) {
            outputBoundary.presentReportResult(new ReportAccountOutputData(false, "Description cannot be empty."));
            return;
        }

        // Save the report to the repository
        boolean success = repository.saveReport(reportedUserId, issueType, description);

        // Respond based on the save operation's success
        if (success) {
            outputBoundary.presentReportResult(new ReportAccountOutputData(true, "Report submitted successfully."));
        } else {
            outputBoundary.presentReportResult(new ReportAccountOutputData(false, "Failed to submit the report."));
        }
    }
}
