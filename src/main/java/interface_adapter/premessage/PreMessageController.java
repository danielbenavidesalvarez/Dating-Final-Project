package interface_adapter.premessage;

import use_case.premessage.PreMessageInputBoundary;
import use_case.premessage.PreMessageInputData;

/**
 * The controller for the PreMessage Use Case.
 */
public class PreMessageController {

    private final PreMessageInputBoundary preMessageUseCaseInteractor;

    public PreMessageController(PreMessageInputBoundary preMessageUseCaseInteractor) {
        this.preMessageUseCaseInteractor = preMessageUseCaseInteractor;
    }

    /**
     * Executes the PreMessage Use Case.
     * @param username1 the first username involved in the messaging
     * @param username2 the second username involved in the messaging
     */
    public void execute(String username1, String username2) {
        final PreMessageInputData preMessageInputData = new PreMessageInputData(
                username1, username2);

        preMessageUseCaseInteractor.execute(preMessageInputData);
    }
}