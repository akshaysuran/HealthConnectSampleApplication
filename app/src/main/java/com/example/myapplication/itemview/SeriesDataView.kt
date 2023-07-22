package com.example.myapplication.itemview

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.*
import java.text.SimpleDateFormat

class SeriesDataView(context: Context, seriesData: SeriesData) : LinearLayout(context) {
    private var startTime: TextView ?= null
    private var endTime: TextView ?= null
    private var packageName: TextView ?= null
    private var dataSize: TextView ?= null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.series_data_item, this)
        startTime = findViewById(R.id.start_time)
        endTime = findViewById(R.id.end_time)
        dataSize = findViewById(R.id.data_size)
        packageName = findViewById(R.id.package_name)


        startTime?.text = "start time : " + HspUtils.getDateTime(seriesData.startTime)
        endTime?.text = "end time : " + HspUtils.getDateTime(seriesData.endTime)
        dataSize?.text = "data size : " + seriesData.dataSize
        packageName?.text = "package name : " + seriesData.pkgName
    }



}