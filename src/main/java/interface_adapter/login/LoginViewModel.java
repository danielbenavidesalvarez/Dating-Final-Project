package interface_adapter.login;

import interface_adapter.ViewModel;

public class LoginViewModel extends ViewModel<LoginState> {
    public LoginViewModel() {
        super("Log in");
        this.setState(new LoginState());
    }

}
