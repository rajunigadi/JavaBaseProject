package com.raju.javabaseproject.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.raju.javabaseproject.R
import com.raju.javabaseproject.data.utils.SharedPrefsHelper
import com.raju.javabaseproject.utlities.Constants

import javax.inject.Inject

import dagger.android.AndroidInjection
import timber.log.Timber

class MyFCMMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    @Inject
    lateinit var notificationUtils: NotificationUtils

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onNewToken(s: String?) {
        Timber.d("FCM Token: " + s!!)
        sharedPrefsHelper!!.put(Constants.PrefsKeys.KEY_FCM_TOKEN, s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Timber.d("From: " + remoteMessage!!.from!!)
        proceedWithMessage(remoteMessage)
    }

    private fun proceedWithMessage(remoteMessage: RemoteMessage) {
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Timber.d("Message data payload: " + remoteMessage.data)

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                prepareNotification(remoteMessage.data)
            } else {
                // Handle message within 10 seconds
                prepareNotification(remoteMessage.data)
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Timber.d("Message Notification Body: " + remoteMessage.notification!!.body!!)
            notificationUtils!!.sendNotification(this, resources.getString(R.string.app_name), remoteMessage.notification!!.body!!)
        }
    }

    private fun prepareNotification(data: Map<String, String>) {
        for (o in data.entries) {
            val entry = o as Map.Entry<*, *>
            val key = entry.key
            val value = entry.value

            Timber.d("Key: " + key!!)
            val sKey = key.toString()
            val sValue = value.toString()
            try {
                Timber.d("sValue: $sValue")
                notificationUtils?.sendNotification(this, resources.getString(R.string.app_name), sValue)
            } catch (ex: Exception) {
                notificationUtils?.sendNotification(this, resources.getString(R.string.app_name), sKey)
            }
        }
    }
}
