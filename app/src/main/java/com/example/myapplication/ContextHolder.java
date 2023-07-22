package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.List;

public class ContextHolder {@SuppressLint("StaticFieldLeak")
private static Context sContext;
    private static String sProcessName;
    private static boolean sIsMainProcess = false;

    /**
     * return application context.
     *
     * @return context
     */
    public static Context getContext() {
        return sContext;
    }

    /**
     * Core should be initialized with application context.
     */
    public static void setContext(Application application) {
        setContextCore(application.getApplicationContext());
        application.registerActivityLifecycleCallbacks(ActivityRefCounter.INSTANCE);
        setProcessName(getAndroidProcessName(sContext));
    }

    public static void setContextCore(Context context) {
        sContext = context;
    }

    /**
     * return process name.
     *
     * @return process name
     */
    public static String getProcessName() {
        return sProcessName;
    }

    /**
     * application process name.
     */
    @VisibleForTesting
    public static void setProcessName(String processName) {
        sProcessName = processName;
        if (processName != null && !processName.contains("remote")) {
            sIsMainProcess = true;
        }
    }

    /**
     * return whether this process is main process or not
     *
     * @return true if main process
     */
    public static boolean isMainProcess() {
        return sIsMainProcess;
    }

    private static String getAndroidProcessName(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return Application.getProcessName();
        }
        return getAppNameByPid(context, android.os.Process.myPid());
    }

    private static String getAppNameByPid(@NonNull Context context, int pid) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        if (activityManager != null) {
            List<ActivityManager.RunningAppProcessInfo> processes = activityManager.getRunningAppProcesses();
            if (processes != null) {
                for (ActivityManager.RunningAppProcessInfo processInfo : processes) {
                    if (processInfo.pid == pid) {
                        return processInfo.processName;
                    }
                }
            }
        }

        return "";
    }

}
