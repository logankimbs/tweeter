package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import static edu.byu.cs.tweeter.client.model.service.backgroundTask.PagedTask.ITEMS_KEY;
import static edu.byu.cs.tweeter.client.model.service.backgroundTask.PagedTask.MORE_PAGES_KEY;

import android.os.Bundle;
import android.os.Looper;

import java.util.List;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.observer.PagedObserver;

public class PagedHandler<T> extends BackgroundTaskHandler<PagedObserver<T>> {
    public PagedHandler(Looper looper, PagedObserver<T> observer) {
        super(looper, observer);
    }

    @Override
    protected void handleSuccess(Bundle data, PagedObserver<T> observer) {
        List<T> items = (List<T>) data.getSerializable(ITEMS_KEY);
        boolean hasMorePages = data.getBoolean(MORE_PAGES_KEY);
        observer.handleSuccess(items, hasMorePages);
    }
}
