package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodPressure
import com.example.myapplication.item.Water
import com.example.myapplication.item.Weight
import java.text.SimpleDateFormat

class WeightView(context: Context, weight: Weight) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var weightView: TextView ?= null


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.weight_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        weightView = findViewById(R.id.weight)


        startTime?.text = "start time : " + HspUtils.getDateTime(weight.startTime)
        weightView?.text = "weight : " + weight.weight
        packageName?.text = "package name : " + weight.pkgName
    }



}