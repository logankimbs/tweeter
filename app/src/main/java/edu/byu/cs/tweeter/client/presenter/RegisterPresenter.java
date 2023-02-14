package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.RegisterService;
import edu.byu.cs.tweeter.model.domain.User;

public class RegisterPresenter {
    private View view;
    private RegisterService registerService;

    public RegisterPresenter(View view) {
        this.view = view;
        registerService = new RegisterService();
    }

    public interface View {
        void displayMessage(String message);
        void register(User user);
    }

    public void register(String firstName, String lastName, String alias, String password, String imageBytesBase64) {
        registerService.register(firstName, lastName, alias, password, imageBytesBase64, new RegisterServiceObserver());
    }

    private class RegisterServiceObserver implements RegisterService.Observer {
        @Override
        public void displayMessage(String message) {
            view.displayMessage(message);
        }

        @Override
        public void register(User user) {
            view.register(user);
        }
    }
}
