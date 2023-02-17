package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Looper;

import edu.byu.cs.tweeter.client.model.service.MainService;

public class GetFollowersCountHandler extends CountHandler {
    public GetFollowersCountHandler(MainService.GetFollowersCountObserver observer) {
        super(Looper.getMainLooper(), observer);
    }
}