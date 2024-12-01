package interface_adapter;

public class ViewManagerModel extends ViewModel<String> {

    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }

    public void navigateToLikeView() {
        setState("LikeView");
        firePropertyChanged();
    }

    public void navigateToAnalyticsView() {
        setState("AnalyticsView");
        firePropertyChanged();
    }

    public void navigateToReportAccountView() { // New method
        setState("ReportAccountView");
        firePropertyChanged();
    }
}

