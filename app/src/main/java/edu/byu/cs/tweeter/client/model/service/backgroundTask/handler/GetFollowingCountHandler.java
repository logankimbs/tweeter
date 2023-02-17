package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Looper;

import edu.byu.cs.tweeter.client.model.service.MainService;

public class GetFollowingCountHandler extends CountHandler {
    public GetFollowingCountHandler(MainService.GetFollowingCountObserver observer) {
        super(Looper.getMainLooper(), observer);
    }
}
