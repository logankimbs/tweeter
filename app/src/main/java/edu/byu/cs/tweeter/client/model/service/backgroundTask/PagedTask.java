package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Bundle;
import android.os.Handler;

import java.io.Serializable;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

public abstract class PagedTask<T> extends AuthenticatedTask {
    public static final String ITEMS_KEY = "items";
    public static final String MORE_PAGES_KEY = "more-pages";

    private User targetUser;
    private int limit;
    private T lastItem;
    private List<T> items;
    private boolean hasMorePages;

    public PagedTask(Handler messageHandler, AuthToken authToken, User targetUser, int limit, T lastFollowee) {
        super(messageHandler, authToken);
        this.targetUser = targetUser;
        this.limit = limit;
        this.lastItem = lastFollowee;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public int getLimit() {
        return limit;
    }

    public T getLastItem() {
        return lastItem;
    }

    protected abstract Pair<List<T>, Boolean> getItems();

    @Override
    protected void processTask() {
        Pair<List<T>, Boolean> pageOfUsers = getItems();
        items = pageOfUsers.getFirst();
        hasMorePages = pageOfUsers.getSecond();
    }

    @Override
    protected void loadSuccessBundle(Bundle msgBundle) {
        msgBundle.putSerializable(ITEMS_KEY, (Serializable) items);
        msgBundle.putBoolean(MORE_PAGES_KEY, hasMorePages);
    }
}
