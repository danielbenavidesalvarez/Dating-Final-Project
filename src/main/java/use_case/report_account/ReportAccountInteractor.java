package use_case.report_account;

import data_access.FirebaseUserDataAccessObject;
import data_access.ReportAccountRepository;

public class ReportAccountInteractor implements ReportAccountInputBoundary {
    private final ReportAccountOutputBoundary outputBoundary;
    private final FirebaseUserDataAccessObject firebaseDataAccess; // Replace with FirebaseUserDataAccessObject
    private final ReportAccountUserDataAccessInterface userDataAccess;

    public ReportAccountInteractor(
            ReportAccountOutputBoundary outputBoundary,
            FirebaseUserDataAccessObject firebaseDataAccess, // Use Firebase DAO
            ReportAccountUserDataAccessInterface userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.firebaseDataAccess = firebaseDataAccess;
        this.userDataAccess = userDataAccess;
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

        // Save report using Firebase
        boolean success = firebaseDataAccess.saveReport(reportedUserId, issueType, description);

        if (success) {
            outputBoundary.presentReportResult(new ReportAccountOutputData(true, "Report submitted successfully."));
        } else {
            outputBoundary.presentReportResult(new ReportAccountOutputData(false, "Failed to submit the report."));
        }
    }
}
