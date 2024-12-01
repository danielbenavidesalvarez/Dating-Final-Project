package interface_adapter.report_account;

import use_case.report_account.ReportAccountOutputBoundary;
import use_case.report_account.ReportAccountOutputData;

public class ReportAccountPresenter implements ReportAccountOutputBoundary {
    private final ReportAccountViewModel viewModel;

    public ReportAccountPresenter(ReportAccountViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentReportResult(ReportAccountOutputData outputData) {
        viewModel.setSuccess(outputData.isSuccess());
        viewModel.setMessage(outputData.getMessage());
        viewModel.notifyObservers();
    }
}
