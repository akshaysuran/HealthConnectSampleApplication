package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.ActivitySession
import com.example.myapplication.item.BloodPressure
import com.example.myapplication.item.SleepSession
import java.text.SimpleDateFormat

class ActivitySessionView(context: Context, activitySession: ActivitySession) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var endTime: TextView ?= null
    private var packageName: TextView ?= null
    private var activityType: TextView ?= null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.activity_session_item, this)
        startTime = findViewById(R.id.start_time)
        endTime = findViewById(R.id.end_time)
        activityType = findViewById(R.id.activity_type)
        packageName = findViewById(R.id.package_name)


        startTime?.text = "start time : " + HspUtils.getDateTime(activitySession.startTime)
        endTime?.text = "end time : " + HspUtils.getDateTime(activitySession.endTime)
        activityType?.text = "activity type : " + activitySession.activityType
        packageName?.text = "package name : " + activitySession.pkgName
    }



}