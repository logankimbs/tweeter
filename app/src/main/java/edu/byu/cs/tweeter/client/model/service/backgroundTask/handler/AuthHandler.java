package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import static edu.byu.cs.tweeter.client.model.service.backgroundTask.AuthenticationTask.AUTH_TOKEN_KEY;
import static edu.byu.cs.tweeter.client.model.service.backgroundTask.AuthenticationTask.USER_KEY;

import android.os.Bundle;
import android.os.Looper;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.AuthObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class AuthHandler extends BackgroundTaskHandler<AuthObserver> {
    public AuthHandler(Looper looper, AuthObserver observer) {
        super(looper, observer);
    }

    @Override
    protected void handleSuccess(Bundle data, AuthObserver observer) {
        User user = (User) data.getSerializable(USER_KEY);
        AuthToken authToken = (AuthToken) data.getSerializable(AUTH_TOKEN_KEY);
        observer.handleSuccess(user, authToken);
    }
}
