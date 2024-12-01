package data_access;

public interface ReportAccountRepository {
    /**
     * Saves a report to the repository.
     *
     * @param reportedUserId The ID of the reported user.
     * @param issueType      The type of issue being reported.
     * @param description    A detailed description of the issue.
     * @return true if the report was saved successfully, false otherwise.
     */
    boolean saveReport(String reportedUserId, String issueType, String description);
}
