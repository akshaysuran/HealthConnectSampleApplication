package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodPressure
import com.example.myapplication.item.SleepSession
import java.text.SimpleDateFormat

class SleepSessionView(context: Context, sleepSession: SleepSession) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var endTime: TextView ?= null
    private var packageName: TextView ?= null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.sleep_session_item, this)
        startTime = findViewById(R.id.start_time)
        endTime = findViewById(R.id.end_time)
        packageName = findViewById(R.id.package_name)


        startTime?.text = "start time : " + HspUtils.getDateTime(sleepSession.startTime)
        endTime?.text = "end time : " + HspUtils.getDateTime(sleepSession.endTime)
        packageName?.text = "package name : " + sleepSession.pkgName
    }



}