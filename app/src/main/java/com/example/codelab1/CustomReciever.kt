package com.example.codelab1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.lang.StringBuilder

class CustomReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append("customRec ${intent.action}\n")
            Toast.makeText(context, toString(), Toast.LENGTH_SHORT).show()
        }
    }
}