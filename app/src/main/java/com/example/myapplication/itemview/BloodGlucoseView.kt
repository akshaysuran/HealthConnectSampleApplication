package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.BloodGlucose
import com.example.myapplication.item.BloodPressure
import java.text.SimpleDateFormat

class BloodGlucoseView(context: Context, bloodGlucose: BloodGlucose) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var packageName: TextView ?= null
    private var level: TextView ?= null
    private var mealType: TextView ?= null
    private var relationToMeal: TextView ?= null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.blood_glucose_item, this)
        startTime = findViewById(R.id.start_time)
        packageName = findViewById(R.id.package_name)
        level = findViewById(R.id.level)
        mealType = findViewById(R.id.meal_type)
        relationToMeal = findViewById(R.id.relation_meal)

        startTime?.text = "start time : " + HspUtils.getDateTime(bloodGlucose.startTime)
        packageName?.text = "package name : " + bloodGlucose.pkgName
        level?.text = "level : " + bloodGlucose.level
        mealType?.text = "meal type : " + bloodGlucose.mealType
        relationToMeal?.text = "relation to meal : " + bloodGlucose.relationToMeal
    }



}