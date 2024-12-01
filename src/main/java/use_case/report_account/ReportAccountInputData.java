package use_case.report_account;

public class ReportAccountInputData {
    private final String reportedUserId;
    private final String issueType;
    private final String description;

    public ReportAccountInputData(String reportedUserId, String issueType, String description) {
        this.reportedUserId = reportedUserId;
        this.issueType = issueType;
        this.description = description;
    }

    public String getReportedUserId() {
        return reportedUserId;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getDescription() {
        return description;
    }
}
