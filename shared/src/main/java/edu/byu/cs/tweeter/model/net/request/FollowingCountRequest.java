package edu.byu.cs.tweeter.model.net.request;

public class FollowingCountRequest {
    private String userAlias;

    private FollowingCountRequest() {}

    public FollowingCountRequest(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
}
