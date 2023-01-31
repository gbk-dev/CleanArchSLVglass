package com.example.cleanarchslvglass.presentation.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "FCM"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e(TAG, token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification != null) {
            Log.e(TAG, "Title: " + message.notification!!.title!!)
            Log.e(TAG, "Body: " + message.notification!!.body!!)
        }

        if (message.data.isNotEmpty()) {
            Log.e(TAG, "Data: " + message.data)
        }
    }

}