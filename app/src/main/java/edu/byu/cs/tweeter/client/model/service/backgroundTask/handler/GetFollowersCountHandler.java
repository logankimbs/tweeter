package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import edu.byu.cs.tweeter.client.model.service.MainService;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowersCountTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.ServiceObserver;

public class GetFollowersCountHandler extends BackgroundTaskHandler {
    public GetFollowersCountHandler(MainService.GetFollowersCountObserver observer) {
        super(Looper.getMainLooper(), observer);
    }

    @Override
    protected void handleSuccess(Bundle data, ServiceObserver observer) {
        int count = data.getInt(GetFollowersCountTask.COUNT_KEY);
        ((MainService.GetFollowersCountObserver) observer).handleSuccess(count);
    }
}