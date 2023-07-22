package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodPressure
import com.example.myapplication.item.Height
import com.example.myapplication.item.Water
import com.example.myapplication.item.Weight
import java.text.SimpleDateFormat

class HeightView(context: Context, height: Height) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var heightView: TextView ?= null


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.height_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        heightView = findViewById(R.id.height)


        startTime?.text = "start time : " + HspUtils.getDateTime(height.startTime)
        heightView?.text = "height : " + height.height + " meter"
        packageName?.text = "package name : " + height.pkgName
    }



}