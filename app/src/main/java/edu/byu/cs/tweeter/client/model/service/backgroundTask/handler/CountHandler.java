package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;
import android.os.Looper;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.CountObserver;

public class CountHandler extends BackgroundTaskHandler<CountObserver> {
    public CountHandler(Looper looper, CountObserver observer) {
        super(looper, observer);
    }

    @Override
    protected void handleSuccess(Bundle data, CountObserver observer) {
        observer.handleSuccess(20);
    }
}
