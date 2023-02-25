package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.MainService;

public class MainPresenterUnitTest {

    // Testing logout following video example
    private MainPresenter.View mockView;
    private MainService mockMainService;
    private Cache mockCache;
    private MainPresenter mainPresenterSpy;

    @BeforeEach
    public void setup() {
        mockView = Mockito.mock(MainPresenter.View.class);
        mockMainService = Mockito.mock(MainService.class);
        mockCache = Mockito.mock(Cache.class);
        mainPresenterSpy = Mockito.spy(new MainPresenter(mockView));

        Mockito.when(mainPresenterSpy.getMainService()).thenReturn(mockMainService);
        Cache.setInstance(mockCache);
    }

    @Test
    public void testLogout_successful() {
        Answer<Void> answer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                MainPresenter.LogoutServiceObserver observer = invocation.getArgument(0, MainPresenter.LogoutServiceObserver.class);
                observer.handleSuccess();
                return null;
            }
        };

        setupLogout(answer);
        Mockito.verify(mockCache).clearCache();
        // Mockito.verify(mockView).clearMessage();
        Mockito.verify(mockView).logout();
    }

    @Test
    public void testLogout_failed() {
        Answer<Void> answer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                MainPresenter.LogoutServiceObserver observer = invocation.getArgument(0, MainPresenter.LogoutServiceObserver.class);
                observer.handleFailure("the error message");
                return null;
            }
        };

        setupLogout(answer);
        verifyMessageResult("Failed to logout: the error message");
    }

    @Test
    public void testLogout_exception() {
        Answer<Void> answer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                MainPresenter.LogoutServiceObserver observer = invocation.getArgument(0, MainPresenter.LogoutServiceObserver.class);
                observer.handleException(new Exception("the exception message"));
                return null;
            }
        };

        setupLogout(answer);
        verifyMessageResult("Failed to logout because of exception: the exception message");
    }

    private void verifyMessageResult(String message) {
        Mockito.verify(mockCache, Mockito.times(0)).clearCache();
        // Mockito.verify(mockView).clearMessage();
        Mockito.verify(mockView).displayMessage(message);
    }

    private void setupLogout(Answer<Void> answer) {
        Mockito.doAnswer(answer).when(mockMainService).logout(Mockito.any(MainPresenter.LogoutServiceObserver.class));
        mainPresenterSpy.logout();
        Mockito.verify(mockView).displayMessage("Logging out...");
    }
}
