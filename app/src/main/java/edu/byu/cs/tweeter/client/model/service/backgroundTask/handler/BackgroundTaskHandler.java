package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.BackgroundTask;
import edu.byu.cs.tweeter.client.model.service.observer.ServiceObserver;

public abstract class BackgroundTaskHandler<T extends ServiceObserver> extends Handler {
    private T observer;

    public BackgroundTaskHandler(T observer) {
        super(Looper.getMainLooper());
        this.observer = observer;
    }

    protected abstract void handleSuccessMessage(T observer, Bundle data);

    @Override
    public void handleMessage(android.os.Message msg) {
        boolean success = msg.getData().getBoolean(BackgroundTask.SUCCESS_KEY);

        if (success) {
            handleSuccessMessage(observer, msg.getData());
        } else if (msg.getData().containsKey(BackgroundTask.MESSAGE_KEY)) {
            String message = msg.getData().getString(BackgroundTask.MESSAGE_KEY);
            observer.handleFailure(message);
        } else if (msg.getData().containsKey(BackgroundTask.EXCEPTION_KEY)) {
            Exception ex = (Exception) msg.getData().getSerializable(BackgroundTask.EXCEPTION_KEY);
            observer.handleException(ex);
        }
    }
}
