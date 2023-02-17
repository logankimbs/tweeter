package edu.byu.cs.tweeter.client.model.service;

import android.os.Looper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetStoryTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.GetStoryHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.PagedHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class StatusService {
    public interface Observer {
        void displayMessage(String message);
        void addStatuses(List<Status> statuses, boolean hasMorePages);
    }

    public interface GetStoryObserver extends PagedObserver<Status> {
        void handleSuccess(List<Status> statuses, boolean hasMorePages);
        void handleFailure(String message);
        void handleException(Exception ex);
    }

    public void loadMoreItems(User user, int pageSize, Status lastStatus, PagedObserver<Status> observer) {
        GetStoryTask getStoryTask = new GetStoryTask(Cache.getInstance().getCurrUserAuthToken(),
                user, pageSize, lastStatus, new PagedHandler<>(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(getStoryTask);
    }
}
