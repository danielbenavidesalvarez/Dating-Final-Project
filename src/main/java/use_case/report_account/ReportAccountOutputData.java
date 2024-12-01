package use_case.report_account;

public class ReportAccountOutputData {
    private final boolean success;
    private final String message;

    public ReportAccountOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
