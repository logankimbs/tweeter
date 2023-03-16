package edu.byu.cs.tweeter.model.net.request;

public class IsFollowerRequest {
    private String followerAlias;
    private String followeeAlias;

    private IsFollowerRequest() {}

    public IsFollowerRequest(String followerAlias, String followeeAlias) {
        this.followerAlias = followerAlias;
        this.followeeAlias = followeeAlias;
    }

    public String getFollowerAlias() {
        return followerAlias;
    }

    public String getFolloweeAlias() {
        return followeeAlias;
    }

    public void setFollowerAlias(String followerAlias) {
        this.followerAlias = followerAlias;
    }

    public void setFolloweeAlias(String followeeAlias) {
        this.followeeAlias = followeeAlias;
    }
}
