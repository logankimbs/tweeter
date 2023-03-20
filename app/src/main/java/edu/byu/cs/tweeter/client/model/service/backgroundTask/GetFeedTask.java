package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import edu.byu.cs.tweeter.client.model.service.StatusService;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.request.GetFeedRequest;
import edu.byu.cs.tweeter.model.net.response.GetFeedResponse;
import edu.byu.cs.tweeter.util.Pair;

/**
 * Background task that retrieves a page of statuses from a user's feed.
 */
public class GetFeedTask extends PagedTask<Status> {
    private static final String LOG_TAG = "GetFeedTask";

    public GetFeedTask(AuthToken authToken, User targetUser, int limit, Status lastStatus, Handler messageHandler) {
        super(messageHandler, authToken, targetUser, limit, lastStatus);
    }

    @Override
    protected Pair<List<Status>, Boolean> getItems() {
        return getFakeData().getPageOfStatus(getLastItem(), getLimit());
//        try {
//            String targetUserAlias = targetUser == null ? null : targetUser.getAlias();
//            Status lastStatusAlias = lastItem == null ? null : lastItem;
//
//            GetFeedRequest request = new GetFeedRequest(authToken, targetUserAlias, limit, lastStatusAlias);
//            GetFeedResponse response = getServerFacade().getFeed(request, StatusService.GET_FEED_URL);
//
//            if (response.isSuccess()) {
//                this.items = response.getFeed();
//                this.hasMorePages = response.getHasMorePages();
//                sendSuccessMessage();
//            } else {
//                System.out.println("GetFeedTask: " + response.getMessage());
//                sendFailedMessage(response.getMessage());
//            }
//
//            return new Pair<>(items, hasMorePages);
//        } catch (IOException | TweeterRemoteException ex) {
//            Log.e(LOG_TAG, "Failed to get feed", ex);
//            sendExceptionMessage(ex);
//        }
//
//        return null;
    }
}
