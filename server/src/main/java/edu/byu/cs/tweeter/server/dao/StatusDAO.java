package edu.byu.cs.tweeter.server.dao;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.request.GetFeedRequest;
import edu.byu.cs.tweeter.model.net.response.GetFeedResponse;
import edu.byu.cs.tweeter.util.FakeData;
import edu.byu.cs.tweeter.util.Pair;

public class StatusDAO {
    public GetFeedResponse getFeed(GetFeedRequest request) {
        // TODO: Generates dummy data for now. Replace with a real implementation later.
        Pair<List<Status>, Boolean> feed = getFakeData().getPageOfStatus(request.getLastStatus(), request.getLimit());
        return new GetFeedResponse(feed.getFirst(), feed.getSecond());


//        assert request.getLimit() > 0;
//        assert request.getUserAlias() != null;
//
//        List<Status> allStatuses = getDummyStatuses();
//        List<Status> responseStatuses = new ArrayList<>(request.getLimit());
//
//        boolean hasMorePages = false;
//
//        if(request.getLimit() > 0) {
//            if (allStatuses != null) {
//                int statusIndex = getStatusStartingIndex(request.getLastStatusAlias(), allStatuses);
//
//                for(int limitCounter = 0; statusIndex < allStatuses.size() && limitCounter < request.getLimit(); statusIndex++, limitCounter++) {
//                    responseStatuses.add(allStatuses.get(statusIndex));
//                }
//
//                hasMorePages = statusIndex < allStatuses.size();
//            }
//        }
//
//        return new GetFeedResponse(responseStatuses, hasMorePages);
    }

    private int getStatusStartingIndex(String lastStatusAlias, List<Status> allStatuses) {

        int statusIndex = 0;

        if(lastStatusAlias != null) {
            // This is a paged request for something after the first page. Find the first item
            // we should return
            for (int i = 0; i < allStatuses.size(); i++) {
                if(lastStatusAlias.equals(allStatuses.get(i).getUser().getAlias())) {
                    // We found the index of the last item returned last time. Increment to get
                    // to the first one we should return
                    statusIndex = i + 1;
                    break;
                }
            }
        }

        return statusIndex;
    }

    List<Status> getDummyStatuses() {
        return getFakeData().getFakeStatuses();
    }

    FakeData getFakeData() {
        return FakeData.getInstance();
    }
}
