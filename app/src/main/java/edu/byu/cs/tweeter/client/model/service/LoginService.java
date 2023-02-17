package edu.byu.cs.tweeter.client.model.service;

import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.LoginTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.AuthHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.AuthObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class LoginService {
    public interface Observer {
        void displayMessage(String message);
        void handleSuccess(User user, AuthToken authToken);
        void login(User user);
    }

    public interface LoginObserver extends AuthObserver {
        void handleSuccess(User user, AuthToken authToken);
        void handleFailure(String message);
        void handleException(Exception ex);
    }

    public void login(String alias, String password, AuthObserver observer) {
        LoginTask loginTask = new LoginTask(alias, password, new AuthHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(loginTask);
    }
}
