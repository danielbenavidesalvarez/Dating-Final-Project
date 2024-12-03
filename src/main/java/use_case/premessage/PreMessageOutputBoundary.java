package use_case.premessage;

/**
 * Output Boundary for actions related to presenting the PreMessage Use Case results.
 */
public interface PreMessageOutputBoundary {

    /**
     * Prepares the view for a successful pre-message action.
     * @param response the input data for the successful action
     */
    void presentSuccessView(PreMessageInputData response);

    /**
     * Prepares the view for a failed pre-message action.
     * @param error the error message to display
     */
    void presentFailView(String error);
}
