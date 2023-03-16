package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;
import android.os.Looper;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.IsFollowerTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.BooleanObserver;

public class BooleanHandler extends BackgroundTaskHandler<BooleanObserver> {
    public BooleanHandler(Looper looper, BooleanObserver observer) {
        super(looper, observer);
    }

    @Override
    protected void handleSuccess(Bundle data, BooleanObserver observer) {
//        boolean value = data.getBoolean(IsFollowerTask.IS_FOLLOWER_KEY);
//        observer.handleSuccess(value);

        observer.handleSuccess(data.getBoolean("isFollower"));

    }
}
