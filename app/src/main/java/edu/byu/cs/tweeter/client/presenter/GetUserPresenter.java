package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.UserService;
import edu.byu.cs.tweeter.model.domain.User;

public class GetUserPresenter {
    private View view;
    private UserService userService;

    public GetUserPresenter(View view) {
        this.view = view;
        userService = new UserService();
    }

    public interface View {
        void displayMessage(String message);
        void displayUser(User user);
    }

    public void displayUser(String userAlias) {
        userService.getUser(userAlias, new GetUserObserver());
    }

    private class GetUserObserver implements UserService.Observer {
        @Override
        public void displayMessage(String message) {
            view.displayMessage(message);
        }

        @Override
        public void handleSuccess(User user) {
            view.displayUser(user);
        }
    }
}
