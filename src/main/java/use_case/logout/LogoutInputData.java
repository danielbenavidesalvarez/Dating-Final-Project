package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private final String username;

    public LogoutInputData(String username) {
        // Save the current username in an instance variable.
        this.username = username;
    }

    // Getter method for the username.
    public String getUsername() {
        return this.username;
    }
}

//package use_case.logout;
//
///**
// * The Input Data for the Logout Use Case.
// */
//public class LogoutInputData {
//
//    public LogoutInputData(String username) {
//        // TODO: save the current username in an instance variable and add a getter.
//    }
//
//}
