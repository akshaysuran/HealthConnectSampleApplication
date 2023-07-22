package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodPressure
import com.example.myapplication.item.SleepSession
import com.example.myapplication.item.SleepStage
import java.text.SimpleDateFormat

class SleepStageView(context: Context, sleepStage: SleepStage) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var endTime: TextView ?= null
    private var packageName: TextView ?= null
    private var stage: TextView ?= null


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.sleep_stage_item, this)
        startTime = findViewById(R.id.start_time)
        endTime = findViewById(R.id.end_time)
        packageName = findViewById(R.id.package_name)
        stage = findViewById(R.id.stage)


        startTime?.text = "start time : " + HspUtils.getDateTime(sleepStage.startTime)
        endTime?.text = "end time : " +  HspUtils.getDateTime(sleepStage.endTime)
        packageName?.text = "package name : " + sleepStage.pkgName
        stage?.text = "stage : " + sleepStage.stage
    }



}