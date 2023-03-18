package edu.byu.cs.tweeter.model.net.request;

public class GetUserRequest {
    private String alias;

    private GetUserRequest() {}

    public GetUserRequest(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
