package com.example.myapplication.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainInputActivityBinding
import com.example.myapplication.viewmodel.MainInputActivityViewModel


class MainInputActivity : AppCompatActivity() {

    private var mBinding: ActivityMainInputActivityBinding?= null
    private var mViewModel: MainInputActivityViewModel? = null
    private var nutritionBtn: Button ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_input_activity)
        mViewModel = ViewModelProvider(this).get(MainInputActivityViewModel::class.java)
        supportActionBar?.title = "Insert data screen"
        mBinding?.singleHydration?.visibility = View.GONE
        mBinding?.multipleHydration?.visibility = View.GONE
        setClickListener()
    }

    private fun setClickListener() {
        mBinding?.apply {
            singleExercise.setOnClickListener {
                mViewModel?.insertSingleExerciseData()
            }
            singleSleep.setOnClickListener {
                mViewModel?.insertSleepRecord()
            }
            multipleSleep.setOnClickListener {
                mViewModel?.insertSleepRecordData()
            }
            singleHr.setOnClickListener {
                mViewModel?.insertSeriesHeartRate()
            }
            singleBp.setOnClickListener {
                mViewModel?.insertBloodPressureRecord()
            }
            multipleBp.setOnClickListener {
                mViewModel?.insertBloodPressureRecordList()
            }
            singleBg.setOnClickListener {
                mViewModel?.insertBloodGlucoseRecord()
            }
            multipleBg.setOnClickListener {
                mViewModel?.insertBloodGlucoseRecordList()
            }
            singleSpo2.setOnClickListener {
                mViewModel?.insertSingleSpo2Record()
            }
            multipleSpo2.setOnClickListener {
                mViewModel?.insertMultipleSpo2RecordList()
            }
            singleWeight.setOnClickListener {
                mViewModel?.insertSingleWeightRecord()
            }
            multipleWeight.setOnClickListener {
                mViewModel?.insertMultipleWeightRecordList()
            }
            singleHydration.setOnClickListener {
                mViewModel?.insertSingleHydrationRecord()
            }
            multipleHydration.setOnClickListener {
                mViewModel?.insertMultipleHydrationRecord()
            }
            singleNutrition.setOnClickListener {
                mViewModel?.insertNutritionRecord()
            }
        }
    }

}