package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class RemoteReceiver : BroadcastReceiver() {
    private val TAG = "HSPTEST-RemoteReceiver"
    override fun onReceive(context: Context, intent: Intent) {

        val action = intent.action ?: return

        if(action == "com.samsung.android.app.shealth.intent.action.NOTIFICATION_NOT_USEFUL_CLICKED") {
            Log.d(TAG, "action is aciton")
        }

    }
}