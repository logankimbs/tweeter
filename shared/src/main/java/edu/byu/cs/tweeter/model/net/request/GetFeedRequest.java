package edu.byu.cs.tweeter.model.net.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

public class GetFeedRequest {

    private AuthToken authToken;
    private String userAlias;
    private int limit;
    private String lastStatusAlias;

    private GetFeedRequest() {}

    public GetFeedRequest(AuthToken authToken, String userAlias, int limit, String lastStatusAlias) {
        this.authToken = authToken;
        this.userAlias = userAlias;
        this.limit = limit;
        this.lastStatusAlias = lastStatusAlias;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getLastStatusAlias() {
        return lastStatusAlias;
    }

    public void setLastStatusAlias(String lastStatusAlias) {
        this.lastStatusAlias = lastStatusAlias;
    }
}
