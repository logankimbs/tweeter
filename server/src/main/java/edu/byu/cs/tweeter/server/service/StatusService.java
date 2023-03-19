package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.net.request.GetFeedRequest;
import edu.byu.cs.tweeter.model.net.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.net.response.GetFeedResponse;
import edu.byu.cs.tweeter.model.net.response.PostStatusResponse;
import edu.byu.cs.tweeter.server.dao.StatusDAO;

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
        } else if (request.getUserAlias() == null) {
            throw new RuntimeException("[Bad Request] Missing a userAlias");
        } else if (request.getLimit() <= 0) {
            throw new RuntimeException("[Bad Request] Request needs to have a positive limit");
        } else if (request.getLastStatusAlias() == null) {
            throw new RuntimeException("[Bad Request] Missing a lastStatusAlias");
        }

        return getStatusDAO().getFeed(request);
    }

    public StatusDAO getStatusDAO() {
        return new StatusDAO();
    }
}
