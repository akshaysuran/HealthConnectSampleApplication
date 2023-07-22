package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodPressure
import java.text.SimpleDateFormat

class BloodPressureView(context: Context, bloodPressure: BloodPressure) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var systolic: TextView ?= null
    private var diastolic: TextView ?= null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.blood_pressure_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        systolic = findViewById(R.id.systolic)
        diastolic = findViewById(R.id.diastolic)

        startTime?.text = "start time : " + HspUtils.getDateTime(bloodPressure.startTime)
        systolic?.text = "systolic : " + bloodPressure.systolic
        diastolic?.text = "diastolic : " + bloodPressure.diastolic
        packageName?.text = "package name : " + bloodPressure.pkgName
    }



}