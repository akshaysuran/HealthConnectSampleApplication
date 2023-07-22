package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodPressure
import com.example.myapplication.item.BodyFat
import com.example.myapplication.item.Water
import com.example.myapplication.item.Weight
import java.text.SimpleDateFormat

class BodyFatView(context: Context, bodyFat: BodyFat) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var bodyFatView: TextView ?= null


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.body_fat_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        bodyFatView = findViewById(R.id.body_fat)


        startTime?.text = "start time : " + HspUtils.getDateTime(bodyFat.startTime)
        bodyFatView?.text = "body fat : " + bodyFat.bodyFat
        packageName?.text = "package name : " + bodyFat.pkgName
    }



}