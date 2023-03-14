package edu.byu.cs.tweeter.model.net.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

public class FollowersRequest {

    private AuthToken authToken;
    private String followeeAlias;
    private int limit;
    private String lastFollowerAlias;

    /**
     * Allows construction of the object from Json. Private so it won't be called in normal code.
     */
    private FollowersRequest() {}

    /**
     * Creates an instance.
     *
     * @param followeeAlias the alias of the user whose followers are to be returned.
     * @param limit the maximum number of followers to return.
     * @param lastFollowerAlias the alias of the last follower that was returned in the previous request (null if
     *                     there was no previous request or if no followers were returned in the
     *                     previous request).
     */
    public FollowersRequest(AuthToken authToken, String followeeAlias, int limit, String lastFollowerAlias) {
        this.authToken = authToken;
        this.followeeAlias = followeeAlias;
        this.limit = limit;
        this.lastFollowerAlias = lastFollowerAlias;
    }

    /**
     * Returns the auth token of the user who is making the request.
     *
     * @return the auth token.
     */
    public AuthToken getAuthToken() {
        return authToken;
    }

    /**
     * Sets the auth token.
     *
     * @param authToken the auth token.
     */
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    /**
     * Returns the followee whose followers are to be returned by this request.
     *
     * @return the followee.
     */
    public String getFolloweeAlias() {
        return followeeAlias;
    }

    /**
     * Sets the followee.
     *
     * @param followeeAlias the followee.
     */
    public void setFolloweeAlias(String followeeAlias) {
        this.followeeAlias = followeeAlias;
    }

    /**
     * Returns the maximum number of followers to return.
     *
     * @return the maximum number of followers to return.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the maximum number of followers to return.
     *
     * @param limit the maximum number of followers to return.
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Returns the alias of the last follower that was returned in the previous request.
     *
     * @return the alias of the last follower that was returned in the previous request.
     */
    public String getLastFollowerAlias() {
        return lastFollowerAlias;
    }

    /**
     * Sets the alias of the last follower that was returned in the previous request.
     *
     * @param lastFollowerAlias the alias of the last follower that was returned in the previous request.
     */
    public void setLastFollowerAlias(String lastFollowerAlias) {
        this.lastFollowerAlias = lastFollowerAlias;
    }
}
