package edu.byu.cs.tweeter.client.model.service;

import android.os.Looper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.FollowTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowersCountTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.GetFollowingCountTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.IsFollowerTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.LogoutTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.PostStatusTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.UnfollowTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.CountHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.GetFollowersCountHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.GetFollowingCountHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.IsFollowerHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.LogoutHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.PostStatusHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.handler.SimpleNotificationHandler;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.CountObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.ServiceObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.SimpleNotificationObserver;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class MainService {
    public interface Observer {
        void displayMessage(String message);
        void isFollower(boolean isFollower);
        void updateSelectedUserFollowingAndFollowers();
        void updateFollowButton(boolean isFollower);
        void logout();
        void followerCount(int count);
        void followeeCount(int count);
        void handleUnfollow();
        void handleFollow();
    }




    public interface FollowObserver extends SimpleNotificationObserver {
        void handleSuccess();
        void handleFailure(String message);
        void handleException(Exception ex);
    }

    public void follow(User selectedUser, FollowObserver observer) {
        FollowTask followTask = new FollowTask(Cache.getInstance().getCurrUserAuthToken(),
                selectedUser, new SimpleNotificationHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(followTask);
    }




    public interface UnfollowObserver extends SimpleNotificationObserver {
        void handleSuccess();
        void handleFailure(String message);
        void handleException(Exception ex);
    }

    public void unfollow(User selectedUser, UnfollowObserver observer) {
        System.out.println("Unfollow");
        UnfollowTask unfollowTask = new UnfollowTask(Cache.getInstance().getCurrUserAuthToken(),
                selectedUser, new SimpleNotificationHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(unfollowTask);
    }




    public interface IsFollowerObserver extends ServiceObserver {
        void handleSuccess(boolean isFollower);
        void handleFailure(String message);
        void handleException(Exception ex);
    }

    public void isFollower(User selectedUser, Observer observer) {
        IsFollowerTask isFollowerTask = new IsFollowerTask(Cache.getInstance().getCurrUserAuthToken(),
                Cache.getInstance().getCurrUser(), selectedUser, new IsFollowerHandler(observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(isFollowerTask);
    }




    public interface LogoutObserver extends SimpleNotificationObserver {
        void handleSuccess();
        void handleFailure(String message);
        void handleException(Exception ex);
    }

    public void logout(LogoutObserver observer) {
        LogoutTask logoutTask = new LogoutTask(Cache.getInstance().getCurrUserAuthToken(), new LogoutHandler(observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(logoutTask);
    }





    public interface PostStatusObserver extends ServiceObserver {
        void handleSuccess();
        void handleFailure(String message);
        void handleException(Exception ex);
    }

    public void postStatus(String post, Observer observer) {
        Status newStatus = new Status(post, Cache.getInstance().getCurrUser(), System.currentTimeMillis(), parseURLs(post), parseMentions(post));
        PostStatusTask statusTask = new PostStatusTask(Cache.getInstance().getCurrUserAuthToken(),
                newStatus, new PostStatusHandler(observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(statusTask);
    }




    public interface GetFollowersCountObserver extends CountObserver {
        void handleSuccess(int count);
        void handleFailure(String message);
        void handleException(Exception ex);
    }

    public void getFollowersCount(User selectedUser, GetFollowersCountObserver observer) {
        GetFollowersCountTask followersCountTask = new GetFollowersCountTask(Cache.getInstance().getCurrUserAuthToken(),
                selectedUser, new CountHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(followersCountTask);
    }





    public interface GetFollowingCountObserver extends CountObserver {
        void handleSuccess(int count);
        void handleFailure(String message);
        void handleException(Exception ex);
    }

    public void getFollowingCount(User selectedUser, GetFollowingCountObserver observer) {
        GetFollowingCountTask followingCountTask = new GetFollowingCountTask(Cache.getInstance().getCurrUserAuthToken(),
                selectedUser, new CountHandler(Looper.getMainLooper(), observer));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(followingCountTask);
    }








//    public void getCounts(User selectedUser, Observer observer) {
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//
//        // Get count of most recently selected user's followers.
//        GetFollowersCountTask followersCountTask = new GetFollowersCountTask(Cache.getInstance().getCurrUserAuthToken(),
//                selectedUser, new GetFollowersCountHandler(observer));
//        executor.execute(followersCountTask);
//
//        // Get count of most recently selected user's followees (who they are following)
//        GetFollowingCountTask followingCountTask = new GetFollowingCountTask(Cache.getInstance().getCurrUserAuthToken(),
//                selectedUser, new GetFollowingCountHandler(observer));
//        executor.execute(followingCountTask);
//    }





    private String getFormattedDateTime() throws ParseException {
        SimpleDateFormat userFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat statusFormat = new SimpleDateFormat("MMM d yyyy h:mm aaa");

        return statusFormat.format(userFormat.parse(LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 8)));
    }

    private List<String> parseURLs(String post) {
        List<String> containedUrls = new ArrayList<>();
        for (String word : post.split("\\s")) {
            if (word.startsWith("http://") || word.startsWith("https://")) {
                int index = findUrlEndIndex(word);
                word = word.substring(0, index);

                containedUrls.add(word);
            }
        }

        return containedUrls;
    }

    private List<String> parseMentions(String post) {
        List<String> containedMentions = new ArrayList<>();

        for (String word : post.split("\\s")) {
            if (word.startsWith("@")) {
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                word = "@".concat(word);

                containedMentions.add(word);
            }
        }

        return containedMentions;
    }

    private int findUrlEndIndex(String word) {
        if (word.contains(".com")) {
            int index = word.indexOf(".com");
            index += 4;
            return index;
        } else if (word.contains(".org")) {
            int index = word.indexOf(".org");
            index += 4;
            return index;
        } else if (word.contains(".edu")) {
            int index = word.indexOf(".edu");
            index += 4;
            return index;
        } else if (word.contains(".net")) {
            int index = word.indexOf(".net");
            index += 4;
            return index;
        } else if (word.contains(".mil")) {
            int index = word.indexOf(".mil");
            index += 4;
            return index;
        } else {
            return word.length();
        }
    }
}
