package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodPressure
import com.example.myapplication.item.Water
import java.text.SimpleDateFormat

class WaterView(context: Context, water: Water) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var volume: TextView ?= null


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.water_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        volume = findViewById(R.id.volume)


        startTime?.text = "start time : " + HspUtils.getDateTime(water.startTime)
        volume?.text = "volume : " + water.volume
        packageName?.text = "package name : " + water.pkgName
    }



}