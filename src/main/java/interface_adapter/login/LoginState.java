package interface_adapter.login;

public class LoginState {

    private String username = "";
    private String loginError;
    private String password = "";

    public String getUsername(){return username; }

    public String getLoginError(){return loginError; }

    public String getPassword() {return password; }

    public void setUsername(String username){ this.username = username; }

    public void setLoginError(String loginError){ this.loginError = loginError; }

    public void setPassword(String password){ this.password = password; }
}
