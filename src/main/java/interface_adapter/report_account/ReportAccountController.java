package interface_adapter.report_account;

import use_case.report_account.ReportAccountInputBoundary;
import use_case.report_account.ReportAccountInputData;

public class ReportAccountController {
    private final ReportAccountInputBoundary interactor;

    public ReportAccountController(ReportAccountInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void reportAccount(String reportedUserId, String issueType, String description) {
        ReportAccountInputData inputData = new ReportAccountInputData(reportedUserId, issueType, description);
        interactor.reportAccount(inputData);
    }
}
