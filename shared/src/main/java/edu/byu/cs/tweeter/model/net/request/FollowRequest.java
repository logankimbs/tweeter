package edu.byu.cs.tweeter.model.net.request;

public class FollowRequest {
    private String followeeAlias;

    private FollowRequest() {}

    public FollowRequest(String followeeAlias) {
        this.followeeAlias = followeeAlias;
    }

    public String getFolloweeAlias() {
        return followeeAlias;
    }

    public void setFolloweeAlias(String followeeAlias) {
        this.followeeAlias = followeeAlias;
    }
}
