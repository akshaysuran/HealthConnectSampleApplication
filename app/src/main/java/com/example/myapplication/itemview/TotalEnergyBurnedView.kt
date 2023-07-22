package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.TotalEnergyBurned

class TotalEnergyBurnedView(context: Context, energyBurned: TotalEnergyBurned) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var endTime: TextView ?= null
    private var packageName: TextView ?= null
    private var calorie: TextView ?= null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.total_energy_burned_item, this)
        startTime = findViewById(R.id.start_time)
        endTime = findViewById(R.id.end_time)
        calorie = findViewById(R.id.calorie)
        packageName = findViewById(R.id.package_name)


        startTime?.text = "start time : " + HspUtils.getDateTime(energyBurned.startTime)
        endTime?.text = "end time : " + HspUtils.getDateTime(energyBurned.endTime)
        calorie?.text = "calorie : " + energyBurned.calorie
        packageName?.text = "package name : " + energyBurned.pkgName
    }



}