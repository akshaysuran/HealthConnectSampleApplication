package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodPressure
import com.example.myapplication.item.SampleHeartRate
import com.example.myapplication.item.Water
import java.text.SimpleDateFormat

class SampleHeartRateView(context: Context, sampleHeartRate: SampleHeartRate) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var heartRate: TextView ?= null


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.sample_heart_rate_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        heartRate = findViewById(R.id.heart_rate)

        startTime?.text = "start time : " + HspUtils.getDateTime(sampleHeartRate.startTime)
        heartRate?.text = "heart rate : " + sampleHeartRate.heartRate
        packageName?.text = "package name : " + sampleHeartRate.pkgName
    }



}