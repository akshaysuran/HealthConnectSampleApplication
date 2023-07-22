package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.Record
import com.example.myapplication.databinding.ActivityViewMainBinding
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "HSPTEST-MainActivity"
    }

    private val requestPermission = registerForActivityResult(PermissionController.createRequestPermissionResultContract()) {
        grantedPermissions: Set<String> ->
        DataSyncManager.getAllSyncToken()
        Log.i(TAG, "granted Pemrission list : ${grantedPermissions.size}")
    }

    private var mBinding: ActivityViewMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_main)
        supportActionBar?.title = "Data view screen"
        setClickListener()
        ObjectWatcher.context = this

        val intent = Intent("com.samsung.android.app.shealth.intent.action.NOTIFICATION_NOT_USEFUL_CLICKED").apply {
            setPackage(BuildConfig.APPLICATION_ID)
        }
        sendBroadcast(intent)
        Log.i(TAG, "onCreate()")
    }

    private fun setClickListener() {
        mBinding?.apply {
            activitySession.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.ActivitySession)
            }

            sleepSession.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.SleepSession)
            }
            sleepStage.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.SleepStage)
            }

            heartRate.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.HeartRate)
            }

            bloodPressure.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.BloodPressure)
            }
            bloodGlucose.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.BloodGlucose)
            }
            bloodOxygen.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.BloodOxygen)
            }
            weight.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.Weight)
            }
            nutrition.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.Nutrition)
            }

            distance.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.Distance)
            }

            totalEnergyBurned.setOnClickListener {
                startViewActivity(HspConstants.ItemTypes.TotalEnergyBurned)
            }
        }
    }

    private fun startViewActivity(itemType: Int) {
        val intent = Intent("android.intent.action.MAIN_LIST")
        intent.setPackage(ContextHolder.getContext().packageName)
        intent.putExtra("itemType", itemType)

        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.permission_btn) {
            requestPermissions()
        }
        if (item.itemId == R.id.main_view) {
            val intent = Intent("android.intent.action.MAIN_VIEW")
            intent.setPackage(ContextHolder.getContext().packageName)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun requestPermissions() {
        try {
            requestPermission.launch(populatePermissionList())
        } catch (ex: Exception) {
            Log.e(TAG, "exception occurred $ex")
        }
    }

    private fun populatePermissionList(): Set<String> {
        val returnValue = mutableListOf<String>()
        HspUtils.getDataPermissionList().forEach { recordType ->
            returnValue.add(HealthPermission.getReadPermission(recordType))
            returnValue.add(HealthPermission.getWritePermission(recordType))
        }
        Log.i(TAG, "size = ${returnValue.size}")
        return returnValue.toSet()
    }

}