package edu.byu.cs.tweeter.model.net.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class GetFeedRequest {
    private AuthToken authToken;
    private User user;
    private int limit;
    private Status lastStatus;

    private GetFeedRequest() {}

    public GetFeedRequest(AuthToken authToken, User user, int limit, Status lastStatus) {
        this.authToken = authToken;
        this.user = user;
        this.limit = limit;
        this.lastStatus = lastStatus;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public User getUser() {
        return user;
    }

    public int getLimit() {
        return limit;
    }

    public Status getLastItem() {
        return lastStatus;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLastItem(Status lastStatus) {
        this.lastStatus = lastStatus;
    }
}
