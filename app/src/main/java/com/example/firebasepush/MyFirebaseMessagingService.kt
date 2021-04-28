package com.example.firebasepush


import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val CHANNEL_ID = "myFirebaseChannel"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("Token", token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.notification != null){
            getFirebaseMessage(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }
    val  notificationID = Random.nextInt()
    fun getFirebaseMessage( title : String?, msg : String?){
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_5g)
            .setContentTitle(title)
            .setContentText(msg)
            .setAutoCancel(true).build()

        val manager : NotificationManagerCompat = NotificationManagerCompat.from(this)
        manager.notify(notificationID , notification)
    }

   /* @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager : NotificationManagerCompat){
        val channelName = "FirebaseNotificationChannel"
        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "My Channel description"
            enableLights(true)
            lightColor = Color.RED
        }
        notificationManager.createNotificationChannel(channel)
    } */
}