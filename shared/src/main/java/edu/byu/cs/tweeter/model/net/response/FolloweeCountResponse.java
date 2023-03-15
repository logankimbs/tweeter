package edu.byu.cs.tweeter.model.net.response;

public class FolloweeCountResponse extends Response {
    private int count;

    // 400 Bad Request
    private FolloweeCountResponse(String message) {
        super(false, message);
    }

    // 200 OK
    public FolloweeCountResponse(int count, boolean success) {
        super(success);
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
