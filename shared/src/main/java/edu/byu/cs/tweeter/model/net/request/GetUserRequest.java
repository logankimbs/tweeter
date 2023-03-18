package edu.byu.cs.tweeter.model.net.request;

public class GetUserRequest {
    private String userAlias;

    private GetUserRequest() {}

    public GetUserRequest(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getUserAlias() {
        return userAlias;
    }
}
