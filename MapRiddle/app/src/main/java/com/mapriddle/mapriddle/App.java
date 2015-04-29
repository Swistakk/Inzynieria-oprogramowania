package com.mapriddle.mapriddle;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Main class used to enable Parse libraries
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "pCndNpLBkQMRykaaOclYxYHXUzYZ8iYCySTzvqGF", "6ZgGtMmvbW0nCBivgEXhUsoOET6UNhJWQQW4qPOL");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
