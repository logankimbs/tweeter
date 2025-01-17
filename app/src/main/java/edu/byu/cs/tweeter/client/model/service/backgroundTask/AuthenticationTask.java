package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

public abstract class AuthenticationTask extends BackgroundTask {
    public static final String USER_KEY = "user";
    public static final String AUTH_TOKEN_KEY = "auth-token";

    private User authenticatedUser;
    private AuthToken authToken;

    protected final String username;
    protected final String password;

    protected AuthenticationTask(Handler messageHandler, String username, String password) {
        super(messageHandler);
        this.username = username;
        this.password = password;
    }

    protected abstract Pair<User, AuthToken> runAuthenticationTask();

    @Override
    protected final void processTask() {
        Pair<User, AuthToken> loginResult = runAuthenticationTask();

        authenticatedUser = loginResult.getFirst();
        authToken = loginResult.getSecond();

        sendSuccessMessage();
    }

    @Override
    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(USER_KEY, authenticatedUser);
        msgBundle.putSerializable(AUTH_TOKEN_KEY, authToken);
    }
}
