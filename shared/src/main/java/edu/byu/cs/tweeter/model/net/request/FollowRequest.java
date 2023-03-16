package edu.byu.cs.tweeter.model.net.request;

public class FollowRequest {
    private String followerAlias;
    private String userToFollowAlias;

    private FollowRequest() {}

    public FollowRequest(String followerAlias, String userToFollowAlias) {
        this.followerAlias = followerAlias;
        this.userToFollowAlias = userToFollowAlias;
    }

    public String getFollowerAlias() {
        return followerAlias;
    }

    public String getUserToFollowAlias() {
        return userToFollowAlias;
    }

    public void setFollowerAlias(String followerAlias) {
        this.followerAlias = followerAlias;
    }

    public void setUserToFollowAlias(String userToFollowAlias) {
        this.userToFollowAlias = userToFollowAlias;
    }
}
