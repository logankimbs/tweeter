package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;
import android.os.Looper;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetUserTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.UserObserver;
import edu.byu.cs.tweeter.model.domain.User;

public class UserHandler extends BackgroundTaskHandler<UserObserver> {
    public UserHandler(Looper looper, UserObserver observer) {
        super(looper, observer);
    }

    @Override
    protected void handleSuccess(Bundle data, UserObserver observer) {
        User user = (User) data.getSerializable(GetUserTask.USER_KEY);
        observer.handleSuccess(user);
    }
}
