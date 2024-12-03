package use_case.premessage;

/**
 * The Input Data for the PreMessage Use Case.
 */
public class PreMessageInputData {

    private final String username1;
    private final String username2;

    public PreMessageInputData(String username1, String username2) {
        this.username1 = username1;
        this.username2 = username2;
    }

    public String getUsername1() {
        return username1;
    }

    public String getUsername2() {
        return username2;
    }
}
