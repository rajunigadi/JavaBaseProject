package com.raju.javabaseproject.data.utils

import android.app.Activity
import android.os.Bundle

import com.google.firebase.analytics.FirebaseAnalytics

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseAnalyticsUtil @Inject
constructor(private val mFirebaseAnalytics: FirebaseAnalytics) {

    fun logEvent(type: String, param1: String, param2: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, param1)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, param2)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, type)
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    fun trackScreen(activity: Activity, screenName: String) {
        mFirebaseAnalytics.setCurrentScreen(activity, screenName, null /* class override */)
    }
}

