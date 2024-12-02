package interface_adapter.premessage;

public class PreMessageState {
    private String username1 = "";
    private String username2 = "";
    private String username1Error;
    private String username2Error;

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public String getUsername1Error() {
        return username1Error;
    }

    public void setUsername1Error(String username1Error) {
        this.username1Error = username1Error;
    }

    public String getUsername2Error() {
        return username2Error;
    }

    public void setUsername2Error(String username2Error) {
        this.username2Error = username2Error;
    }

}
