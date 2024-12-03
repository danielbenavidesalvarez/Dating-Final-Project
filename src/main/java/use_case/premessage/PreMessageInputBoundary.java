package use_case.premessage;

/**
 * Input Boundary for actions which are related to pre-message authentication and actions.
 */
public interface PreMessageInputBoundary {

    /**
     * Executes the pre-message use case.
     * @param preMessageInputData the input data
     */
    void execute(PreMessageInputData preMessageInputData);
}
