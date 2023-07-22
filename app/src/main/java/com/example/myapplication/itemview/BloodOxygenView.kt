package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodOxygen
import com.example.myapplication.item.BloodPressure
import java.text.SimpleDateFormat

class BloodOxygenView(context: Context, bloodOxygen: BloodOxygen) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var percentage: TextView ?= null


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.blood_oxygen_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        percentage = findViewById(R.id.percentage)


        startTime?.text = "start time : " + HspUtils.getDateTime(bloodOxygen.startTime)
        percentage?.text = "systolic : " + bloodOxygen.percentage
        packageName?.text = "package name : " + bloodOxygen.pkgName
    }



}