package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.HspConstants
import com.example.myapplication.HspUtils
import com.example.myapplication.R
import com.example.myapplication.item.HealthData
import com.example.myapplication.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MainListActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "HSPTEST-MainListActivity"
    }

    private var listView: ListView ?= null
    private var listAdapter: ListViewAdapter?= null
    private var itemType by Delegates.notNull<Int>()
    private var progressBar: ProgressBar ?= null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)
        supportActionBar?.title = "Data list screen"

        itemType = intent.getIntExtra("itemType", HspConstants.ItemTypes.BloodPressure)

        listView = findViewById(R.id.listView)
        progressBar = findViewById(R.id.progress_bar)

        listAdapter = ListViewAdapter(this, itemType)

        listView?.adapter = listAdapter

        setObservable()
        getHealthData(itemType)
        setOnItemClickLister()
    }

    private fun setOnItemClickLister() {
        listView?.setOnItemLongClickListener { _, _, position, _ ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete?")
            builder.setMessage("Do you want to delete?")
            builder.setNegativeButton("Cencel", null)
            builder.setPositiveButton("Ok") { _, _ ->
                deleteItem(position)
            }
            builder.show()

            return@setOnItemLongClickListener true
        }
    }

    @SuppressLint("CheckResult")
    private fun deleteItem(position: Int) {
        val healthData = listAdapter?.getItem(position) as HealthData
        val uuid = healthData.uuid
        val list = mutableListOf<String>()
        if (uuid != null) {
            list.add(uuid)
            lifecycleScope.launch {
                viewModel.deleteData(HspUtils.getDataype(itemType), list)
                getHealthData(itemType)
            }
        }
    }

    private fun setObservable() {
        viewModel.activitySessionList.observe(this) {
            listAdapter?.setData(it)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }

        viewModel.sleepSessionList.observe(this) {
            listAdapter?.setData(it)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }
        viewModel.sleepStageList.observe(this) {
            listAdapter?.setData(it)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }

        viewModel.bloodPressureList.observe(this) {
            listAdapter?.setData(it)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }
        viewModel.bloodGlucoseList.observe(this) {
            listAdapter?.setData(it)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }
        viewModel.bloodOxygenList.observe(this) {
            listAdapter?.setData(it)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }

        viewModel.weightList.observe(this) {
            listAdapter?.setData(it)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }

        viewModel.sampleHrList.observe(this) {
            listAdapter?.setData(it)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }

        viewModel.nutritionList.observe(this) { list ->
            Log.i(TAG, "nutritionList - observed - size :  " + list.size)
            listAdapter?.setData(list)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }

        viewModel.activityEnergyBurnedList.observe(this) { list ->
            Log.i(TAG, "activityEnergyBurnedList - observed - size :  " + list.size)
            listAdapter?.setData(list)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }

        viewModel.distanceList.observe(this) { list ->
            Log.i(TAG, "distanceList - observed - size :  " + list.size)
            listAdapter?.setData(list)
            listAdapter?.notifyDataSetInvalidated()
            progressBar?.visibility = View.GONE
        }
    }

    private fun getHealthData(itemType: Int) {
        Log.i(TAG, "exerciseData Fetched")
        when(itemType) {
            HspConstants.ItemTypes.ActivitySession -> viewModel.getExerciseSessionRecordCo()
            HspConstants.ItemTypes.BloodPressure -> viewModel.getBloodPressureDataListCo()
            HspConstants.ItemTypes.BloodGlucose -> viewModel.getBloodGlucoseDataListCo()
            HspConstants.ItemTypes.BloodOxygen -> viewModel.getBloodOxygenDataListCo()
            HspConstants.ItemTypes.Nutrition -> viewModel.getNutritionDataListCo()
            HspConstants.ItemTypes.HeartRate -> viewModel.getHeartRateDataListCo()
            HspConstants.ItemTypes.Weight -> viewModel.getWeightDataListCo()
            HspConstants.ItemTypes.SleepSession -> viewModel.getSleepSessionRecordCo()
            HspConstants.ItemTypes.SleepStage -> viewModel.getSleepStageDataListCo()
            HspConstants.ItemTypes.TotalEnergyBurned -> viewModel.getTotalEnergyBurnedRecordCo()
            HspConstants.ItemTypes.Distance -> viewModel.getDistanceRecordCo()
        }
    }
}