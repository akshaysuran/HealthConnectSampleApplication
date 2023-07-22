package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BasalRate
import com.example.myapplication.item.BloodPressure
import com.example.myapplication.item.Water
import com.example.myapplication.item.Weight
import java.text.SimpleDateFormat

class BasalRateView(context: Context, basalRate: BasalRate) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var basalRateView: TextView ?= null


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.basal_rate_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        basalRateView = findViewById(R.id.basal_rate)


        startTime?.text = "start time : " + HspUtils.getDateTime(basalRate.startTime)
        basalRateView?.text = "Basal Rate : " + basalRate.basalRate
        packageName?.text = "package name : " + basalRate.pkgName
    }



}