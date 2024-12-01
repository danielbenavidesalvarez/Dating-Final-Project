package use_case.report_account;

/**
 * Interface for accessing user-related data during the ReportAccount process.
 */
public interface ReportAccountUserDataAccessInterface {
    /**
     * Checks if a user exists with the given user ID.
     *
     * @param userId The ID of the user to check.
     * @return true if the user exists, false otherwise.
     */
    boolean doesUserExist(String userId);
}
