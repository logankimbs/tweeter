package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.MainService;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.BooleanObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.CountObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.SimpleNotificationObserver;
import edu.byu.cs.tweeter.model.domain.User;

public class MainPresenter {
    private View view;
    private MainService mainService;

    public MainPresenter(View view) {
        this.view = view;
        mainService = new MainService();
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

    private class UnfollowServiceObserver implements SimpleNotificationObserver {
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

    private class FollowServiceObserver implements SimpleNotificationObserver {
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
        mainService.logout(new LogoutServiceObserver());
    }

    private class LogoutServiceObserver implements SimpleNotificationObserver {
        @Override
        public void handleSuccess() {
            view.logout();
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



    public void postStatus(String status) {
        mainService.postStatus(status, new MainServiceObserver());
    }

    private class MainServiceObserver implements MainService.Observer {
        @Override
        public void displayMessage(String message) {
            view.displayMessage(message);
        }
    }
}
