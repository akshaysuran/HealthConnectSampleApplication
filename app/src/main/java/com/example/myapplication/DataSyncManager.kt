package com.example.myapplication

import android.os.Build
import android.util.Log
import androidx.health.connect.client.changes.Change
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.Record
import androidx.health.connect.client.request.ChangesTokenRequest
import androidx.health.connect.client.response.InsertRecordsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

object DataSyncManager {
    private val TAG = "HSPTEST-DataSyncManager"


    suspend fun insertExerciseSingleRecord(): InsertRecordsResponse? {
        return DataGenerator.getExerciseRecord().let { recordList ->
            HspHealthDataHelper.insertHealthRecords(recordList)
        }
    }

    suspend fun insertExerciseListRecordToHsp(): InsertRecordsResponse? {
        return DataGenerator.getExerciseRecordList().let { recordList ->
            HspHealthDataHelper.insertHealthRecords(recordList)
        }
    }

    suspend fun insertSleepListRecord(): InsertRecordsResponse? {
        return DataGenerator.getSleepListRecord()?.let { recordList ->
            HspHealthDataHelper.insertHealthRecords(recordList)
        }
    }

    suspend fun insertSleepRecord(): InsertRecordsResponse? {
        return DataGenerator.getSleepRecord()?.let { recordList ->
            HspHealthDataHelper.insertHealthRecords(recordList)
        }
    }

    suspend fun insertBloodGlucoseRecord(): InsertRecordsResponse? {
        return DataGenerator.getBloodGlucoseRecord().let {
            HspHealthDataHelper.insertHealthRecords(it)
        }
    }

    suspend fun insertBloodGlucoseRecordList(): InsertRecordsResponse? {
        return DataGenerator.getBloodGlucoseListRecord().let {
            HspHealthDataHelper.insertHealthRecords(it)
        }
    }

    suspend fun insertHrRecord(): InsertRecordsResponse? {
        return DataGenerator.getSeriesHeartRateRecord().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    suspend fun insertBloodPressureRecord(): InsertRecordsResponse? {
        return DataGenerator.getBloodPressureRecord().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    suspend fun insertMultipleBloodPressureRecord(): InsertRecordsResponse? {
        return DataGenerator.getBloodPressureListRecord().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    suspend fun insertSpo2Record(): InsertRecordsResponse? {
        return DataGenerator.getOxygenSaturationRecord().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    suspend fun insertSpo2RecordList(): InsertRecordsResponse? {
        return DataGenerator.getOxygenSaturationRecordList().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    suspend fun insertWeightRecord(): InsertRecordsResponse? {
        return DataGenerator.getWeightRecord().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    suspend fun insertWeightRecordList(): InsertRecordsResponse? {
        return DataGenerator.getWeightListRecord().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    suspend fun insertHydrationRecordList(): InsertRecordsResponse? {
        return DataGenerator.getHydrationListRecord().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    suspend fun insertHydrationRecord(): InsertRecordsResponse? {
        return DataGenerator.getHydrationRecord().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    suspend fun insertNutritionRecord(): InsertRecordsResponse? {
        return DataGenerator.getNutritionRecord().let { record ->
            HspHealthDataHelper.insertHealthRecords(record)
        }
    }

    //** START ************************** Sync data from HSP to MyApplication ********************************//

    private suspend fun getSyncToken(recordType: KClass<out Record>) {
        Log.i(TAG, "getSyncToken : $recordType")
        try {
            val syncToken =
                HspHealthDataHelper.getChangesToken(ChangesTokenRequest(setOf(recordType)))

            SyncTimeHelper.setSyncToken(recordType, syncToken)
            Log.i(TAG, "Success to get sync token, record type : ${recordType.simpleName}")
        } catch (ex: Exception) {
            Log.e(
                TAG,
                "Failed to get sync token, record type : ${recordType.simpleName}, error : $ex"
            )
        }
    }


    private suspend fun isAvailable(permissions: Set<String>): Boolean {
        if (!isHCSupportable()) {
            Log.i(TAG, "Health connect NOT Available")
            return false
        }

        if (!isPermissionGranted(permissions)) {
            Log.i(TAG, "Permission is Denied")
            return false
        }
        return true
    }

    private fun isHCSupportable(): Boolean {
        if (HspDataClient.getClient() == null) {
            Log.i(TAG, "Hsp client not supported")
            return false
        }
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.P
    }

    private suspend fun isPermissionGranted(permissions: Set<String>): Boolean {
        if (HspDataClient.getClient() == null) {
            Log.i(TAG, "Health Connect is NOT Available")
            return false
        }
        val grantedList = HspDataClient.getClient()?.permissionController?.getGrantedPermissions()
            ?: mutableSetOf()
        Log.i(
            TAG,
            "Request permission size : ${permissions.size}, granted sized : ${grantedList.size}"
        )

        return grantedList.toList().containsAll(permissions.toList())
    }

    private fun getPermissionList(recordType: KClass<out Record>): Set<String> {
        val set = mutableSetOf<String>()
        HspUtils.getHspDataTypesForPermissionOfHsp(recordType).forEach { type ->
            set.add(HealthPermission.getReadPermission(type))
            set.add(HealthPermission.getWritePermission(type))
        }
        return set
    }

    suspend fun getChangesTokenFromHsp(recordType: KClass<out Record>): List<Change> =
        withContext(Dispatchers.IO) {
            val syncToken = SyncTimeHelper.getSyncToken(recordType)
            if (syncToken.isNullOrEmpty()) {
                Log.i(TAG, "sync token is  null or empty")
                getSyncToken(recordType)
                listOf()
            } else {
                getChangesFromHsp(recordType, syncToken)
            }
        }

    private suspend fun getChangesFromHsp(
        recordType: KClass<out Record>,
        syncToken: String
    ): List<Change> = withContext(Dispatchers.IO) {
        try {
            val response = HspHealthDataHelper.readChangeData(syncToken)
            if (response.changesTokenExpired) {
                Log.i(TAG, "token is expired, so request token for ${recordType.simpleName}")
                getSyncToken(recordType)
                listOf()
            } else {
                response.changes
            }
        } catch (ex: Exception) {
            Log.i(TAG, "exception occurred: $ex")
            listOf()
        }
    }

    fun getAllSyncToken() {
        CoroutineScope(Dispatchers.IO).launch {
            HspUtils.getRecordTypeListForSync().forEach { recordType ->
                if (SyncTimeHelper.getSyncToken(recordType) == null) {
                    getSyncToken(recordType)
                }
            }
        }
    }

}