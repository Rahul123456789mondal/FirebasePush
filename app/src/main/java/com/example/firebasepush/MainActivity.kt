package com.example.firebasepush

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "myFirebaseChannel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = "Done"
                if (!task.isSuccessful) {
                    msg = "Failed"
                }
                Log.d("onCreate: ", msg )
            }


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelName = "FirebaseNotificationChannel"
            val channel = NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
                description = "My Channel description"
                enableLights(true)
                lightColor = Color.RED
            }
            notificationManager.createNotificationChannel(channel)
        }

        /*
        *
        * //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val channal : NotificationChannel = NotificationChannel("MyNotification", NotificationManager.IMPORTANCE_DEFAULT)
//
//            val manager : NotificationManager = getSystemService(NotificationManager.class)
//
//        }
        *
        *
        * */

    }
}