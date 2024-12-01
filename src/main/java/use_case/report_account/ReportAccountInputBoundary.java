package use_case.report_account;

public interface ReportAccountInputBoundary {
    /**
     * Processes a report account request.
     *
     * @param inputData The data required to report an account.
     */
    void reportAccount(ReportAccountInputData inputData);
}
