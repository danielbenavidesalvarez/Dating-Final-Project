package interface_adapter.premessage;

import interface_adapter.ViewModel;

/**
 * The View Model for the PreMessage View.
 */
public class PreMessageViewModel extends ViewModel<PreMessageState> {

    public PreMessageViewModel() {
        super("message info");
        setState(new PreMessageState());
    }
}
