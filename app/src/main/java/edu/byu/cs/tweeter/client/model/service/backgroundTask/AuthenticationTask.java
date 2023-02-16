package edu.byu.cs.tweeter.client.model.service.backgroundTask;

import android.os.Handler;

public abstract class AuthenticationTask extends BackgroundTask {
    public AuthenticationTask(Handler messageHandler) {
        super(messageHandler);
    }
}
