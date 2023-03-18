package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.UserService;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.AuthObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class LoginPresenter {
    private View view;
    private UserService service;

    public LoginPresenter(View view) {
        this.view = view;
        service = new UserService();
    }

    public interface View {
        void displayMessage(String message);
        void updateDisplay(User user);
    }

    public void login(String alias, String password) {
        service.login(alias, password, new LoginServiceObserver());
    }

    private class LoginServiceObserver implements AuthObserver {
        @Override
        public void handleSuccess(User user, AuthToken authToken) {
            Cache.getInstance().setCurrUser(user);
            Cache.getInstance().setCurrUserAuthToken(authToken);
            view.updateDisplay(user);
        }

        @Override
        public void handleFailure(String message) {
            view.displayMessage(message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayMessage(ex.getMessage());
        }
    }
}
