package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Looper;

import edu.byu.cs.tweeter.client.model.service.StatusService;
import edu.byu.cs.tweeter.model.domain.Status;

public class GetStoryHandler extends PagedHandler<Status> {
    public GetStoryHandler(StatusService.GetStoryObserver observer) {
        super(Looper.getMainLooper(), observer);
    }
}
