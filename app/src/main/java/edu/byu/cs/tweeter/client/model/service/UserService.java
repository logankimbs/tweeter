package edu.byu.cs.tweeter.client.model.service;

import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetUserTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.UserHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.UserObserver;
import edu.byu.cs.tweeter.model.domain.User;

public class UserService {
    public interface Observer {
        void displayMessage(String message);
        void handleSuccess(User user);
    }

    public void getUser(String userAlias, UserObserver observer) {
        GetUserTask getUserTask = new GetUserTask(Cache.getInstance().getCurrUserAuthToken(),
                userAlias, new UserHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(getUserTask);
    }
}
