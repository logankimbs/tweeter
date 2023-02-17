package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;
import android.os.Looper;

import edu.byu.cs.tweeter.client.model.service.MainService;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.SimpleNotificationObserver;

public class UnfollowHandler extends BackgroundTaskHandler<SimpleNotificationObserver> {
    public UnfollowHandler(MainService.UnfollowObserver observer) {
        super(Looper.getMainLooper(), observer);
    }

    @Override
    protected void handleSuccess(Bundle data, SimpleNotificationObserver observer) {
        observer.handleSuccess();
    }
}
