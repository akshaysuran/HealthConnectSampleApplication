package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log

object HealthLaunchUtils {
    private const val TAG = "HealthLaunchUtils"
    private const val SAMSUNG_HEALTH_PKG_NAME = "com.sec.android.app.shealth"
    private const val WATCH_SETTINGS_DEEP_LINK = "https://shealth.samsung.com/deepLink?sc_id=app.main&action=view&destination=settings.watch&v_s=6.20"

    fun jumpToHealthMenu(context: Context) {
        Log.i(TAG, "jumpToHealthMenu()")
        try {
            context.startActivity(getIntent())
        } catch (e: Exception) {
            Log.i(TAG, "failed to jump to health : $e")
        }
    }

    fun isSupportHealthMenu(context: Context): Boolean {
        val isSupported = false
        try {
            context.packageManager.getPackageInfo(SAMSUNG_HEALTH_PKG_NAME, PackageManager.GET_META_DATA)
            val infos = context.packageManager.queryIntentActivities(getIntent(), 0)
            if (infos.isEmpty()) {
                return false
            }
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }

        return true
    }

    private fun getIntent() =
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(WATCH_SETTINGS_DEEP_LINK)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            setPackage(SAMSUNG_HEALTH_PKG_NAME)
        }

}