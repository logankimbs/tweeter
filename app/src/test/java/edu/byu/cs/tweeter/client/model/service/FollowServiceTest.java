package edu.byu.cs.tweeter.client.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.FakeData;

public class FollowServiceTest {

    private User currentUser;
    private AuthToken currentAuthToken;
    private FollowService followServiceSpy;
    private FollowServiceObserver observer;
    private CountDownLatch countDownLatch;

    @BeforeEach
    public void setup() {
        currentUser = new User("FirstName", "LastName", null);
        currentAuthToken = new AuthToken();
        followServiceSpy = Mockito.spy(new FollowService());
        observer = new FollowServiceObserver();

        resetCountDownLatch();
    }

    private void resetCountDownLatch() {
        countDownLatch = new CountDownLatch(1);
    }

    private void awaitCountDownLatch() throws InterruptedException {
        countDownLatch.await();
        resetCountDownLatch();
    }

    private class FollowServiceObserver implements FollowService.GetFollowingObserver {

        private boolean success;
        private String message;
        private List<User> followees;
        private boolean hasMorePages;
        private Exception exception;

        @Override
        public void handleSuccess(List<User> items, boolean hasMorePages) {
            this.success = true;
            this.message = null;
            this.followees = items;
            this.hasMorePages = hasMorePages;
            this.exception = null;

            countDownLatch.countDown();
        }

        @Override
        public void handleFailure(String message) {
            this.success = false;
            this.message = message;
            this.followees = null;
            this.hasMorePages = false;
            this.exception = null;

            countDownLatch.countDown();
        }

        @Override
        public void handleException(Exception ex) {
            this.success = false;
            this.message = null;
            this.followees = null;
            this.hasMorePages = false;
            this.exception = ex;

            countDownLatch.countDown();
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public List<User> getFollowees() {
            return followees;
        }

        public boolean hasMorePages() {
            return hasMorePages;
        }

        public Exception getException() {
            return exception;
        }
    }

    @Test
    public void testGetFollowing_validRequest_correctResponse() throws InterruptedException {
        followServiceSpy.getFollowing(currentUser, 3, null, observer);
        awaitCountDownLatch();

        List<User> expectedFollowing = FakeData.getInstance().getFakeUsers().subList(0, 3);
        Assertions.assertTrue(observer.isSuccess());
        Assertions.assertNull(observer.getMessage());
        Assertions.assertEquals(expectedFollowing, observer.getFollowees());
        Assertions.assertTrue(observer.hasMorePages());
        Assertions.assertNull(observer.getException());
    }
}
