package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.LoginService;
import edu.byu.cs.tweeter.model.domain.User;

public class LoginPresenter {
    private View view;
    private LoginService loginService;

    public LoginPresenter(View view) {
        this.view = view;
        loginService = new LoginService();
    }

    public interface View {
        void displayMessage(String message);
        void login(User user);
    }

    public void login(String alias, String password) {
        loginService.login(alias, password, new LoginServiceObserver());
    }

    private class LoginServiceObserver implements LoginService.Observer {
        @Override
        public void displayMessage(String message) {
            view.displayMessage(message);
        }

        @Override
        public void login(User user) {
            view.login(user);
        }
    }
}
