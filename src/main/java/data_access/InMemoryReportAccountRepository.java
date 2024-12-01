package data_access;

import java.util.ArrayList;
import java.util.List;

public class InMemoryReportAccountRepository implements ReportAccountRepository {
    private final List<Report> reports;
    private boolean failOnSave = false; // Flag to simulate failure

    public InMemoryReportAccountRepository() {
        this.reports = new ArrayList<>();
    }

    @Override
    public boolean saveReport(String reportedUserId, String issueType, String description) {
        if (failOnSave) {
            return false; // Simulate a failure
        }
        try {
            Report report = new Report(reportedUserId, issueType, description);
            reports.add(report);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to save report: " + e.getMessage());
            return false;
        }
    }

    public List<Report> getAllReports() {
        return new ArrayList<>(reports);
    }

    /**
     * Sets the failOnSave flag to simulate repository save failures.
     *
     * @param failOnSave true to simulate failure, false to behave normally.
     */
    public void setFailOnSave(boolean failOnSave) {
        this.failOnSave = failOnSave;
    }

    public static class Report {
        private final String reportedUserId;
        private final String issueType;
        private final String description;

        public Report(String reportedUserId, String issueType, String description) {
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
}
