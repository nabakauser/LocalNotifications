package com.example.locnotifs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ContentInfoCompat.Flags

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        setContent {
            NotificationButton(context = this, intent = intent)
        }
    }
}

@Composable
fun NotificationButton(context: Context, intent: Intent) {
    val data = intent.getBooleanExtra("CANCEL_ACTION", false)
    Log.d("CancelActions","second notif - ${intent}")
    Column(modifier = Modifier.fillMaxSize(),Arrangement.Center, Alignment.CenterHorizontally) {
        Button(onClick = {
            val i = Intent(context, ActivityTwo::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            context.startActivity(i)
            val notification = createNotification(context)
            displayNotification(notification,context)
        }) {
            Text(text = "Send Notification")
        }
        if(data) {
            Button(onClick = {
                val notification = createCancelNotification(context)
                displayCancelNotification(notification,context)
            }) {
                Text(text = "Cancel Notification")
            }
        }
    }
}

private fun navigateToNextActivity() {

}

