package com.example.codelab1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.lang.StringBuilder

class CustomReciever : BroadcastReceiver() {
    private val ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            Intent.ACTION_POWER_CONNECTED-> showMessage(context,"Connected")
            Intent.ACTION_POWER_DISCONNECTED-> showMessage(context,"Disconnected")
            ACTION_CUSTOM_BROADCAST-> {
                val intFromExtras = intent.getIntExtra("randNumber", 0)
                showMessage(context, "Custom action received" + "\n" +
                        (intFromExtras * intFromExtras))
            }
        }
    }

    private fun showMessage(context: Context, msg: String?){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}

