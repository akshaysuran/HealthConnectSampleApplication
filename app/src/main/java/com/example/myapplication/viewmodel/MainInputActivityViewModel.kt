package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.DataSyncManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainInputActivityViewModel: ViewModel() {
    companion object {
        const val TAG = "MainInputActivityViewModel"
    }

    fun insertSingleExerciseData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertExerciseSingleRecord()
                if(response == null) {
                    Log.i(TAG, " insertSingleExerciseData : failed to insert exercise data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertSingleExerciseData data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "exception in data insertion to hsp $ex")
            }
        }
    }

    fun insertSleepRecordData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertSleepListRecord()
                if(response == null) {
                    Log.i(TAG, " insertSleepRecordData : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertSleepRecordData data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "exception in data insertion to hsp $ex")
            }
        }
    }

    fun insertSleepRecord() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertSleepRecord()
                if(response == null) {
                    Log.i(TAG, " insertSleepRecord : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertSleepRecord data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertSleepRecord $ex")
            }
        }
    }

    fun insertSeriesHeartRate() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertHrRecord()
                if(response == null) {
                    Log.i(TAG, " insertSleepRecord : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertSeriesHeartRate data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertSeriesHeartRate $ex")
            }
        }
    }

    fun insertBloodPressureRecord() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertBloodPressureRecord()
                if(response == null) {
                    Log.i(TAG, " insertSleepRecord : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertBloodPressureRecord data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertBloodPressureRecord $ex")
            }
        }
    }

    fun insertBloodPressureRecordList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertMultipleBloodPressureRecord()
                if(response == null) {
                    Log.i(TAG, " insertSleepRecord : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertBloodPressureRecordList data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertBloodPressureRecordList $ex")
            }
        }
    }

    fun insertBloodGlucoseRecord() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertBloodGlucoseRecord()
                if(response == null) {
                    Log.i(TAG, " insertSleepRecord : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertBloodGlucoseRecord data")
                }
            } catch (ex: Exception) {
                Log.i(TAG, "insertBloodGlucoseRecord $ex")
            }
        }
    }

    fun insertBloodGlucoseRecordList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertBloodGlucoseRecordList()
                if(response == null) {
                    Log.i(TAG, " insertSleepRecord : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertBloodGlucoseRecordList data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertBloodGlucoseRecordList $ex")
            }
        }
    }

    fun insertSingleSpo2Record() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertSpo2Record()
                if(response == null) {
                    Log.i(TAG, " insertMultipleSpo2RecordList : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertSingleSpo2Record data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertSingleSpo2Record")
            }
        }
    }

    fun insertMultipleSpo2RecordList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertSpo2RecordList()
                if (response == null) {
                    Log.i(TAG, " insertMultipleSpo2RecordList : failed to insert sleep data to Health connect"
                    )
                } else {
                    Log.i(TAG, "Successfully inserted insertMultipleSpo2RecordList data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertMultipleSpo2RecordList")
            }
        }
    }

    fun insertSingleWeightRecord() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertWeightRecord()
                if(response == null) {
                    Log.i(TAG, " insertSingleWeightRecord : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertSingleWeightRecord data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertSingleWeightRecord $ex")
            }
        }
    }

    fun insertMultipleWeightRecordList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertWeightRecordList()
                if(response == null) {
                    Log.i(TAG, " insertMultipleWeightRecordList : failed to insert sleep data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertMultipleWeightRecordList data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertMultipleWeightRecordList $ex")
            }
        }
    }

    fun insertSingleHydrationRecord() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertHydrationRecord()
                if(response == null) {
                    Log.i(TAG, " insertSingleHydrationRecord : failed to insert nutrition data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertSingleHydrationRecord data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertSingleHydrationRecord $ex")
            }
        }
    }

    fun insertMultipleHydrationRecord() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertHydrationRecordList()
                if(response == null) {
                    Log.i(TAG, " insertMultipleHydrationRecord : failed to insert nutrition data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertMultipleHydrationRecord data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertMultipleHydrationRecord $ex")
            }
        }
    }

    fun insertNutritionRecord() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = DataSyncManager.insertNutritionRecord()
                if(response == null) {
                    Log.i(TAG, " insertNutritionRecord : failed to insert nutrition data to Health connect")
                } else {
                    Log.i(TAG, "Successfully inserted insertNutritionRecord data")
                }
            } catch (ex: Exception) {
                Log.e(TAG, "insertNutritionRecord $ex")
            }
        }
    }
}