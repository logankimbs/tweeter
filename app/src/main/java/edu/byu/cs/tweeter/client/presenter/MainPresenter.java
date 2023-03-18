package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.MainService;
import edu.byu.cs.tweeter.client.model.service.StatusService;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.BooleanObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.CountObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.SimpleObserver;
import edu.byu.cs.tweeter.model.domain.User;

public class MainPresenter {
    private View view;
    private MainService mainService;
    private StatusService statusService;

    public MainPresenter(View view) {
        this.view = view;
        mainService = getMainService();
        statusService = getStatusService();
    }

    protected MainService getMainService() {
        if (mainService == null) {
            mainService = new MainService();
        }

        return mainService;
    }

    protected StatusService getStatusService() {
        if (statusService == null) {
            statusService = new StatusService();
        }

        return statusService;
    }

    public interface View {
        void displayMessage(String message);
        void isFollower(boolean isFollower);
        void updateSelectedUserFollowingAndFollowers();
        void updateFollowButton(boolean isFollower);
        void logout();
        void followerCount(int count);
        void followeeCount(int count);
    }

    public void getFollowingCount(User selectedUser) {
        mainService.getFollowingCount(selectedUser, new GetFollowingCountObserver());
    }

    private class GetFollowingCountObserver implements CountObserver {
        @Override
        public void handleSuccess(int count) {
            view.followeeCount(count);
        }

        @Override
        public void handleFailure(String message) {
            view.displayMessage(message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayMessage(ex.getMessage());
        }
    }

    public void getFollowersCount(User selectedUser) {
        mainService.getFollowersCount(selectedUser, new GetFollowersCountObserver());
    }

    private class GetFollowersCountObserver implements CountObserver {
        @Override
        public void handleSuccess(int count) {
            view.followerCount(count);
        }

        @Override
        public void handleFailure(String message) {
            view.displayMessage(message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayMessage(ex.getMessage());
        }
    }

    public void isFollower(User selectedUser) {
        mainService.isFollower(selectedUser, new IsFollowerObserver());
    }

    private class IsFollowerObserver implements BooleanObserver {
        @Override
        public void handleSuccess(boolean isFollower) {
            view.updateFollowButton(isFollower);
        }

        @Override
        public void handleFailure(String message) {
            view.displayMessage(message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayMessage(ex.getMessage());
        }
    }

    public void unfollow(User selectedUser) {
        mainService.unfollow(selectedUser, new UnfollowServiceObserver());
    }

    private class UnfollowServiceObserver implements SimpleObserver {
        @Override
        public void handleSuccess() {
            view.updateSelectedUserFollowingAndFollowers();
            view.updateFollowButton(true);
        }

        @Override
        public void handleFailure(String message) {
            view.displayMessage(message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayMessage(ex.getMessage());
        }
    }

    public void follow(User selectedUser) {
        mainService.follow(selectedUser, new FollowServiceObserver());
    }

    private class FollowServiceObserver implements SimpleObserver {
        @Override
        public void handleSuccess() {
            view.updateSelectedUserFollowingAndFollowers();
            view.updateFollowButton(false);
        }

        @Override
        public void handleFailure(String message) {
            view.displayMessage(message);
        }

        @Override
        public void handleException(Exception ex) {
            view.displayMessage(ex.getMessage());
        }
    }

    public void logout() {
        view.displayMessage("Logging out...");
        getMainService().logout(new LogoutServiceObserver());
    }

    public class LogoutServiceObserver implements SimpleObserver {
        @Override
        public void handleSuccess() {
            Cache.getInstance().clearCache();
            // view.clearMessage();
            view.logout();
        }

        @Override
        public void handleFailure(String message) {
            String displayMessage = "Failed to logout: " + message;
            // view.clearMessage();
            view.displayMessage(displayMessage);
        }

        @Override
        public void handleException(Exception ex) {
            String displayMessage = "Failed to logout because of exception: " + ex.getMessage();
            // view.clearMessage();
            view.displayMessage(displayMessage);
        }
    }

    public void postStatus(String status) {
        view.displayMessage("Posting status...");
        getStatusService().postStatus(status, new PostServiceObserver());
    }

    public class PostServiceObserver implements SimpleObserver {
        @Override
        public void handleSuccess() {
            String displayMessage = "Successfully posted!";
            // view.clearMessage();
            view.displayMessage(displayMessage);
        }

        @Override
        public void handleFailure(String message) {
            String displayMessage = "Failed to post status: " + message;
            // view.clearMessage();
            view.displayMessage(displayMessage);
        }

        @Override
        public void handleException(Exception ex) {
            String displayMessage = "Failed to post status because of exception: " + ex.getMessage();
            // view.clearMessage();
            view.displayMessage(displayMessage);
        }
    }
}
