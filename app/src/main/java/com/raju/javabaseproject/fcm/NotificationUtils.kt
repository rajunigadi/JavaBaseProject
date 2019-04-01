package com.raju.javabaseproject.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder

import com.raju.javabaseproject.R
import com.raju.javabaseproject.ui.activities.MainActivity

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationUtils @Inject
constructor() {

    fun sendNotification(context: Context, title: String, content: String) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = getNotificationChannel(context)
            assert(notificationManager != null)
            notificationManager.createNotificationChannel(mChannel!!)
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val mBuilder = NotificationCompat.Builder(context, context.resources.getString(R.string.default_notification_channel_id))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(defaultSoundUri)
                .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)
                .setChannelId(context.resources.getString(R.string.default_notification_channel_id))
                .setContentIntent(pendingIntent)

        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addNextIntent(intent)
        val resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)

        notificationManager.notify(System.currentTimeMillis().toInt(), mBuilder.build())
    }

    private fun getNotificationChannel(context: Context): NotificationChannel? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val description = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(context.getString(R.string.default_notification_channel_id), name, importance)
            channel.description = description
            return channel
        }
        return null
    }

    companion object {

        var NOTIFICATION_ID = 1000
        var REQUEST_CODE = 1999
    }

}
