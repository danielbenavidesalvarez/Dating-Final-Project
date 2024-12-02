package use_case.premessage;

/**
 * Output Data for the PreMessage Use Case.
 */
public class PreMessageOutputData {

    private final String username1;
    private final String username2;
    private final boolean useCaseFailed;

    public PreMessageOutputData(String username1, String username2, boolean useCaseFailed) {
        this.username1 = username1;
        this.username2 = username2;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername1() {
        return username1;
    }

    public String getUsername2() {
        return username2;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
