package com.example.myapplication

import android.app.Activity
import android.app.Application
import android.os.Bundle

object ActivityRefCounter : Application.ActivityLifecycleCallbacks {
    private var mActivityRefCount = 0
    private var mCallback: Application.ActivityLifecycleCallbacks? = null

    fun setLifecycleCallback(callback: Application.ActivityLifecycleCallbacks) {
        mCallback = callback
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        mCallback?.onActivityCreated(activity, bundle)
    }

    override fun onActivityStarted(activity: Activity) {
        increaseRef()
        mCallback?.onActivityStarted(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        mCallback?.onActivityResumed(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        mCallback?.onActivityPaused(activity)
    }

    override fun onActivityStopped(activity: Activity) {
        decreaseRef()
        mCallback?.onActivityStopped(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        mCallback?.onActivitySaveInstanceState(activity, bundle)
    }

    override fun onActivityDestroyed(activity: Activity) {
        mCallback?.onActivityDestroyed(activity)
    }

    private fun increaseRef() {
        mActivityRefCount++
        if (mActivityRefCount <= 0) {
            mActivityRefCount = 1
        }
    }

    private fun decreaseRef() {
        if (mActivityRefCount > 0) {
            mActivityRefCount--
        }
    }

    fun isForeground(): Boolean {
        return mActivityRefCount >= 1
    }

    fun getRefCount(): Int {
        return mActivityRefCount
    }
}