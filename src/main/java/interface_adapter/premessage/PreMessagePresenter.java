package interface_adapter.premessage;

import interface_adapter.ViewManagerModel;
import use_case.premessage.PreMessageOutputBoundary;
import use_case.premessage.PreMessageInputData;
import view.MessageView;

/**
 * The Presenter for the PreMessage Use Case.
 */
public class PreMessagePresenter implements PreMessageOutputBoundary {

    private final PreMessageViewModel preMessageViewModel;
    private final ViewManagerModel viewManagerModel;

    public PreMessagePresenter(ViewManagerModel viewManagerModel, PreMessageViewModel preMessageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.preMessageViewModel = preMessageViewModel;
    }

    @Override
    public void presentSuccessView(PreMessageInputData response) {
        // On success, switch to the messaging view.
        MessageView messageView = new MessageView(response.getUsername1(), response.getUsername2());
//  =      viewManagerModel.addView(messageView.viewName, messageView);
//        viewManagerModel.setState(messageView.viewName);
//        viewManagerModel.firePropertyChanged();
//        this.viewManagerModel.setState("message");
//        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentFailView(String error) {
        final PreMessageState preMessageState = preMessageViewModel.getState();
        preMessageState.setUsername1Error(error);
        preMessageViewModel.firePropertyChanged();
    }
}