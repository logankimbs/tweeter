package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.net.request.GetFeedRequest;
import edu.byu.cs.tweeter.model.net.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.net.response.GetFeedResponse;
import edu.byu.cs.tweeter.model.net.response.PostStatusResponse;

public class StatusService {
    public PostStatusResponse postStatus(PostStatusRequest request) {
        if (request.getStatus() == null) {
            throw new RuntimeException("[Bad Request] Missing a status");
        } else if (request.getStatus().getUser() == null) {
            throw new RuntimeException("[Bad Request] Missing a username");
        } else if (request.getStatus().getPost() == null) {
            throw new RuntimeException("[Bad Request] Missing a post");
        } else if (request.getStatus().getTimestamp() == 0) {
            throw new RuntimeException("[Bad Request] Missing a timestamp");
        } else if (request.getStatus().getUrls() == null) {
            throw new RuntimeException("[Bad Request] Missing a url");
        } else if (request.getStatus().getMentions() == null) {
            throw new RuntimeException("[Bad Request] Missing a mention");
        } else if(request.getAuthToken() == null) {
            throw new RuntimeException("[Bad Request] Missing an auth token");
        } else if (request.getAuthToken().getToken() == null) {
            throw new RuntimeException("[Bad Request] Missing an token");
        } else if (request.getAuthToken().getDatetime() == null) {
            throw new RuntimeException("[Bad Request] Missing an datetime");
        }

        return new PostStatusResponse();
    }

    public GetFeedResponse getFeed(GetFeedRequest request) {
        if (request.getAuthToken() == null) {
            throw new RuntimeException("[Bad Request] Missing an authToken");
        } else if (request.getAuthToken().getToken() == null) {
            throw new RuntimeException("[Bad Request] Missing an token");
        } else if (request.getAuthToken().getDatetime() == null) {
            throw new RuntimeException("[Bad Request] Missing an datetime");
        } else if (request.getUser() == null) {
            throw new RuntimeException("[Bad Request] Missing a user");
        } else if (request.getUser().getFirstName() == null) {
            throw new RuntimeException("[Bad Request] Missing a firstName");
        } else if (request.getUser().getLastName() == null) {
            throw new RuntimeException("[Bad Request] Missing a lastName");
        } else if (request.getUser().getAlias() == null) {
            throw new RuntimeException("[Bad Request] Missing an alias");
        } else if (request.getUser().getImageUrl() == null) {
            throw new RuntimeException("[Bad Request] Missing a imageUrl");
        } else if (request.getLimit() == 0) {
            throw new RuntimeException("[Bad Request] Missing a limit");
        } else if (request.getLastItem() == null) {
            throw new RuntimeException("[Bad Request] Missing a lastStatus");
        }

        // TODO: Generates dummy data. Replace with a real implementation.
        //  THIS IS WRONG!!!
        return new GetFeedResponse(null, false);
    }
}
