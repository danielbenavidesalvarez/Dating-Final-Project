package interface_adapter.report_account;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReportAccountViewModel {
    private boolean success;
    private String message;
    private final PropertyChangeSupport support;

    public ReportAccountViewModel() {
        this.support = new PropertyChangeSupport(this);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        boolean oldSuccess = this.success;
        this.success = success;
        support.firePropertyChange("success", oldSuccess, success);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        String oldMessage = this.message;
        this.message = message;
        support.firePropertyChange("message", oldMessage, message);
    }

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeObserver(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void notifyObservers() {
        support.firePropertyChange("update", null, null);
    }
}
