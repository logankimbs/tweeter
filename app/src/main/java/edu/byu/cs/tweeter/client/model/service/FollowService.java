package edu.byu.cs.tweeter.client.model.service;

import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.FollowTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowersCountTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowersTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowingCountTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowingTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.IsFollowerTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.UnfollowTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.BooleanHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.CountHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.PagedHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.SimpleHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.BooleanObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.CountObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.SimpleObserver;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowService {
    public static final String GET_FOLLOWING_URL = "/getfollowing";
    public static final String GET_FOLLOWERS_URL = "/getfollowers";
    public static final String GET_FOLLOWING_COUNT_URL = "/getfollowingcount";
    public static final String GET_FOLLOWERS_COUNT_URL = "/getfollowerscount";
    public static final String FOLLOW_URL = "/follow";
    public static final String UNFOLLOW_URL = "/unfollow";
    public static final String IS_FOLLOWER_URL = "/isfollower";

    public interface GetFollowingObserver extends PagedObserver<User> {}

    public void getFollowing(User user, int pageSize, User lastFollowee, PagedObserver<User> observer) {
        GetFollowingTask getFollowingTask = new GetFollowingTask(Cache.getInstance().getCurrUserAuthToken(),
                user, pageSize, lastFollowee, new PagedHandler<>(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(getFollowingTask);
    }

    public void getFollowers(User user, int pageSize, User lastFollower, PagedObserver<User> observer) {
        GetFollowersTask getFollowersTask = new GetFollowersTask(Cache.getInstance().getCurrUserAuthToken(),
                user, pageSize, lastFollower, new PagedHandler<>(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(getFollowersTask);
    }

    public void getFollowersCount(User selectedUser, CountObserver observer) {
        GetFollowersCountTask followersCountTask = new GetFollowersCountTask(Cache.getInstance().getCurrUserAuthToken(),
                selectedUser, new CountHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(followersCountTask);
    }

    public void getFollowingCount(User selectedUser, CountObserver observer) {
        GetFollowingCountTask followingCountTask = new GetFollowingCountTask(Cache.getInstance().getCurrUserAuthToken(),
                selectedUser, new CountHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(followingCountTask);
    }

    public void follow(User selectedUser, SimpleObserver observer) {
        FollowTask followTask = new FollowTask(Cache.getInstance().getCurrUserAuthToken(),
                selectedUser, new SimpleHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(followTask);
    }

    public void unfollow(User selectedUser, SimpleObserver observer) {
        UnfollowTask unfollowTask = new UnfollowTask(Cache.getInstance().getCurrUserAuthToken(),
                selectedUser, new SimpleHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(unfollowTask);
    }

    public void isFollower(User selectedUser, BooleanObserver observer) {
        IsFollowerTask isFollowerTask = new IsFollowerTask(Cache.getInstance().getCurrUserAuthToken(),
                Cache.getInstance().getCurrUser(), selectedUser, new BooleanHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(isFollowerTask);
    }
}
