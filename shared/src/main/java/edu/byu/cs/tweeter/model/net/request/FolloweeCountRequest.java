package edu.byu.cs.tweeter.model.net.request;

public class FolloweeCountRequest {
    private String alias;

    /**
     * Allows construction of the object from Json. Private so it won't be called in normal code.
     */
    private FolloweeCountRequest() {}

    public FolloweeCountRequest(String alias) {
        this.alias = alias;
    }

    /**
     * Returns the followee whose followers are to be returned by this request.
     *
     * @return the followee.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the followee.
     *
     * @param alias the followee.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
}
