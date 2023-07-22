package com.example.myapplication

import android.app.Application
import android.util.Log


class ExampleApplication : Application() {
    private val TAG = "HSPTEST-ExampleApplication"
    override fun onCreate() {
        super.onCreate()

        Log.e(TAG, "onCreate()")
        ContextHolder.setContext(this)

    }
}