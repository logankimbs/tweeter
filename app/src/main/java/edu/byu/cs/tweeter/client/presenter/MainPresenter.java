package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.MainService;
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

    public void isFollower(User selectedUser) {
        mainService.isFollower(selectedUser, new MainServiceObserver());
    }





    public void unfollow(User selectedUser) {
        mainService.unfollow(selectedUser, new UnfollowServiceObserver());
    }

    private class UnfollowServiceObserver implements MainService.UnfollowObserver {
        @Override
        public void handleSuccess() {
            view.updateSelectedUserFollowingAndFollowers();
            view.updateFollowButton(true);
            System.out.println("unfollowed");
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

    private class FollowServiceObserver implements MainService.FollowObserver {
        @Override
        public void handleSuccess() {
            view.updateSelectedUserFollowingAndFollowers();
            view.updateFollowButton(false);
            System.out.println("followed");
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


    private void handleUnfollow() {
        view.updateSelectedUserFollowingAndFollowers();
        view.updateFollowButton(true);
    }

    private void handleFollow() {
        view.updateSelectedUserFollowingAndFollowers();
        view.updateFollowButton(false);
    }


    public void logout() {
        mainService.logout(new MainServiceObserver());
    }

    public void postStatus(String status) {
        mainService.postStatus(status, new MainServiceObserver());
    }

    public void getCounts(User selectedUser) {
        mainService.getCounts(selectedUser, new MainServiceObserver());
    }

    private class MainServiceObserver implements MainService.Observer {
        @Override
        public void displayMessage(String message) {
            view.displayMessage(message);
        }

        @Override
        public void isFollower(boolean isFollower) {
            view.isFollower(isFollower);
        }

        @Override
        public void updateSelectedUserFollowingAndFollowers() {
            view.updateSelectedUserFollowingAndFollowers();
        }

        @Override
        public void updateFollowButton(boolean isFollower) {
            view.updateFollowButton(isFollower);
        }

        @Override
        public void logout() {
            view.logout();
        }

        @Override
        public void followerCount(int count) {
            view.followerCount(count);
        }

        @Override
        public void followeeCount(int count) {
            view.followeeCount(count);
        }

        @Override
        public void handleUnfollow() {
            updateSelectedUserFollowingAndFollowers();
            updateFollowButton(true);
        }

        @Override
        public void handleFollow() {
            updateSelectedUserFollowingAndFollowers();
            updateFollowButton(false);
        }
    }
}
