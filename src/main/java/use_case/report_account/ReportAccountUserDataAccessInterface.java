package use_case.report_account;

/**
 * Interface for accessing user-related data during the ReportAccount process.
 */
public interface ReportAccountUserDataAccessInterface {
    boolean doesUserExist(String userId);
    boolean saveReport(String reportedUserId, String issueType, String description); // Add this method
}

