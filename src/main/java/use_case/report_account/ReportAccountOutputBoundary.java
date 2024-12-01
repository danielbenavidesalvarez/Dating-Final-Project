package use_case.report_account;

public interface ReportAccountOutputBoundary {
    /**
     * Presents the result of a report account request.
     *
     * @param outputData The data containing the result of the report operation.
     */
    void presentReportResult(ReportAccountOutputData outputData);
}
