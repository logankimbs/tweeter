package edu.byu.cs.tweeter.client.presenter;

import java.util.List;

import edu.byu.cs.tweeter.client.model.service.StatusService;
import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedObserver;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class GetStoryPresenter {
    private static final int PAGE_SIZE = 10;

    private View view;
    private StatusService statusService;
    private Status lastStatus;
    private boolean hasMorePages;
    private boolean isLoading = false;

    public GetStoryPresenter(View view) {
        this.view = view;
        statusService = new StatusService();
    }

    public interface View {
        void setLoadingFooter(boolean isLoading);
        void displayMessage(String message);
        void addMoreItems(List<Status> followees);
    }

    public void loadMoreItems(User user) {
        if (!isLoading) {
            isLoading = true;
            view.setLoadingFooter(true);
            statusService.loadMoreItems(user, PAGE_SIZE, lastStatus, new GetStoryObserver());
        }
    }

    public void setHasMorePages(boolean hasMorePages) {
        this.hasMorePages = hasMorePages;
    }

    public boolean hasMorePages() {
        return hasMorePages;
    }

    public boolean isLoading() {
        return isLoading;
    }

    private class GetStoryObserver implements PagedObserver<Status> {
        @Override
        public void handleSuccess(List<Status> items, boolean hasMorePages) {
            isLoading = false;
            view.setLoadingFooter(false);
            lastStatus = (items.size() > 0) ? items.get(items.size() - 1) : null;
            setHasMorePages(hasMorePages);
            view.addMoreItems(items);
        }

        @Override
        public void handleFailure(String message) {
            isLoading = false;
            view.setLoadingFooter(false);
            view.displayMessage(message);
        }

        @Override
        public void handleException(Exception ex) {
            isLoading = false;
            view.setLoadingFooter(false);
            view.displayMessage(ex.getMessage());
        }
    }
}
