package data_access;

import use_case.report_account.ReportAccountUserDataAccessInterface;

import java.util.HashSet;
import java.util.Set;

/**
 * In-memory implementation of ReportAccountUserDataAccessInterface.
 */
public class InMemoryReportAccountUserDataAccess implements ReportAccountUserDataAccessInterface {
    private final Set<String> userIds;

    public InMemoryReportAccountUserDataAccess() {
        this.userIds = new HashSet<>();
        // Adding some dummy user IDs for testing
        userIds.add("user123");
        userIds.add("user456");
        userIds.add("user789");
    }

    @Override
    public boolean doesUserExist(String userId) {
        return userIds.contains(userId);
    }

    @Override
    public boolean saveReport(String reportedUserId, String issueType, String description) {
        return false;
    }

    public void addUser(String userId) {
        userIds.add(userId);
    }

    public void removeUser(String userId) {
        userIds.remove(userId);
    }
}
