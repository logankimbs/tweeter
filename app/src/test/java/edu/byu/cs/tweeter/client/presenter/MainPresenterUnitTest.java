package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import edu.byu.cs.tweeter.client.model.service.MainService;

public class MainPresenterUnitTest {
    private MainPresenter.View mockView;
    private MainService mockMainService;
    private MainPresenter mainPresenterSpy;

    @BeforeEach
    public void setup() {
        mockView = Mockito.mock(MainPresenter.View.class);
        mockMainService = Mockito.mock(MainService.class);
        mainPresenterSpy = Mockito.spy(new MainPresenter(mockView));

        Mockito.when(mainPresenterSpy.getMainService()).thenReturn(mockMainService);
    }

    @Test
    public void testPostStatus_successful() throws InterruptedException {
        Answer<Void> answer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                MainPresenter.PostServiceObserver observer = invocation.getArgument(1, MainPresenter.PostServiceObserver.class);
                observer.handleSuccess();
                return null;
            }
        };

        verifyStatus(answer);
        waitThenVerifyMessage("Successfully posted!");
    }

    @Test
    public void testPostStatus_failed() throws InterruptedException {
        Answer<Void> answer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                MainPresenter.PostServiceObserver observer = invocation.getArgument(1, MainPresenter.PostServiceObserver.class);
                observer.handleFailure("the error message");
                return null;
            }
        };

        verifyStatus(answer);
        waitThenVerifyMessage("Failed to post status: the error message");
    }

    @Test
    public void testPostStatus_exception() throws InterruptedException {
        Answer<Void> answer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                MainPresenter.PostServiceObserver observer = invocation.getArgument(1, MainPresenter.PostServiceObserver.class);
                observer.handleException(new Exception("the exception message"));
                return null;
            }
        };

        verifyStatus(answer);
        waitThenVerifyMessage("Failed to post status because of exception: the exception message");
    }

    private void verifyStatus(Answer<Void> answer) {
        Mockito.doAnswer(answer).when(mockMainService).postStatus(Mockito.anyString(), Mockito.any(MainPresenter.PostServiceObserver.class));
        mainPresenterSpy.postStatus("the status");
        Mockito.verify(mockView).displayMessage("Posting status...");
    }

    private void waitThenVerifyMessage(String message) throws InterruptedException {
        Thread.sleep(1000); // Wait for 1 second before verifying the expected interaction
        Mockito.verify(mockView, Mockito.times(1)).displayMessage(message);
    }
}
