package edu.byu.cs.tweeter.client.model.service.backgroundTask.observer;

public interface BooleanObserver extends ServiceObserver {
    void handleSuccess(boolean value);
}
