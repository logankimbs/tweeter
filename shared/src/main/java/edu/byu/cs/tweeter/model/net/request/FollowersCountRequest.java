package edu.byu.cs.tweeter.model.net.request;

public class FollowersCountRequest {
    private String userAlias;

    private FollowersCountRequest() {}

    public FollowersCountRequest(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setAlias(String userAlias) {
        this.userAlias = userAlias;
    }
}
