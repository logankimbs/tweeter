package edu.byu.cs.tweeter.model.net.request;

public class UnfollowRequest {
    private String followeeAlias;

    private UnfollowRequest() {}

    public UnfollowRequest(String followeeAlias) {
        this.followeeAlias = followeeAlias;
    }

    public String getFolloweeAlias() {
        return followeeAlias;
    }

    public void setFolloweeAlias(String followeeAlias) {
        this.followeeAlias = followeeAlias;
    }
}
