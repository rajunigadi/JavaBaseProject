package com.raju.javabaseproject.data.utils;

import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

@Singleton
public class FirebaseAnalyticsUtil {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Inject
    public FirebaseAnalyticsUtil(FirebaseAnalytics firebaseAnalytics) {
        mFirebaseAnalytics = firebaseAnalytics;
    }

    public void logEvent(String type, String param1, String param2) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, param1);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, param2);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, type);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public void trackScreen(Activity activity, String screenName) {
        mFirebaseAnalytics.setCurrentScreen(activity, screenName, null /* class override */);
    }
}

