package com.raju.javabaseproject.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.raju.javabaseproject.R;
import com.raju.javabaseproject.data.utils.SharedPrefsHelper;
import com.raju.javabaseproject.utlities.Constants;

import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Rajashekhar Vanahalli on 07/09/18.
 */
public class MyFCMMessagingService extends FirebaseMessagingService {

    @Inject
    SharedPrefsHelper sharedPrefsHelper;

    @Inject
    NotificationUtils notificationUtils;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public void onNewToken(String s) {
        Timber.d("FCM Token: " + s);
        sharedPrefsHelper.put(Constants.PrefsKeys.KEY_FCM_TOKEN, s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Timber.d("From: " + remoteMessage.getFrom());
        proceedWithMessage(remoteMessage);
    }

    private void proceedWithMessage(RemoteMessage remoteMessage) {
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Timber.d("Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                prepareNotification(remoteMessage.getData());
            } else {
                // Handle message within 10 seconds
                prepareNotification(remoteMessage.getData());
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Timber.d("Message Notification Body: " + remoteMessage.getNotification().getBody());
            notificationUtils.sendNotification(this, getResources().getString(R.string.app_name), remoteMessage.getNotification().getBody());
        }
    }

    private void prepareNotification(Map<String, String> data) {
        for (Object o : data.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Object key = entry.getKey();
            Object value = entry.getValue();

            Timber.d("Key: " + key);
            if (key != null && value != null) {
                String sKey = key.toString();
                String sValue = value.toString();
                try {
                    Timber.d("sValue: " + sValue);
                    notificationUtils.sendNotification(this, getResources().getString(R.string.app_name), sValue);
                } catch (Exception ex) {
                    notificationUtils.sendNotification(this, getResources().getString(R.string.app_name), sKey);
                }
            }
        }
    }
}
