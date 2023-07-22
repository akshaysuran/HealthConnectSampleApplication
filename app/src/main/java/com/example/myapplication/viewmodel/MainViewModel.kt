package com.example.myapplication.viewmodel

import android.util.Log
import androidx.health.connect.client.changes.UpsertionChange
import androidx.health.connect.client.records.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.DataSyncManager
import com.example.myapplication.HspHealthDataHelper
import com.example.myapplication.item.*
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MainViewModel : ViewModel() {
    companion object {
        private val TAG = "HSPTEST-MainViewModel"
    }

    val activitySessionList = MutableLiveData<List<ActivitySession>>()
    val sleepSessionList = MutableLiveData<List<SleepSession>>()
    val sleepStageList = MutableLiveData<List<SleepStage>>()
    val bloodPressureList = MutableLiveData<List<BloodPressure>>()
    val bloodGlucoseList = MutableLiveData<List<BloodGlucose>>()
    val bloodOxygenList = MutableLiveData<List<BloodOxygen>>()
    val sampleHrList = MutableLiveData<List<SampleHeartRate>>()
    val weightList = MutableLiveData<List<Weight>>()
    val nutritionList = MutableLiveData<List<Nutrition>>()
    val activityEnergyBurnedList = MutableLiveData<List<TotalEnergyBurned>>()
    val distanceList = MutableLiveData<List<Distance>>()

    fun getExerciseSessionRecordCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(ExerciseSessionRecord::class)
                val list = mutableListOf<ActivitySession>()
                Log.i(TAG, "exercise list size ${response.size}")
                response.forEachIndexed { _, change ->
                    if (change is UpsertionChange) {
                        (change.record as ExerciseSessionRecord).apply {
                            val activitySession = ActivitySession(
                                startTime.toEpochMilli(),
                                endTime.toEpochMilli(),
                                exerciseType.toString(),
                                metadata.dataOrigin.packageName
                            )
                            activitySession.uuid = metadata.id
                            list.add(activitySession)
                        }
                    }
                    activitySessionList.postValue(list)
                }
            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                activitySessionList.postValue(listOf())
            }
        }
    }


    fun getTotalEnergyBurnedRecordCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(TotalCaloriesBurnedRecord::class)
                val list = mutableListOf<TotalEnergyBurned>()
                Log.i(TAG, "exercise list size ${response.size}")
                response.forEachIndexed { _, change ->
                    if (change is UpsertionChange) {
                        (change.record as TotalCaloriesBurnedRecord).apply {
                            val totalEnergyBurned = TotalEnergyBurned(
                                startTime.toEpochMilli(),
                                endTime.toEpochMilli(),
                                energy.inCalories.toFloat(),
                                metadata.dataOrigin.packageName
                            )
                            totalEnergyBurned.uuid = metadata.id
                            list.add(totalEnergyBurned)
                        }
                    }
                    activityEnergyBurnedList.postValue(list)
                }
            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                activityEnergyBurnedList.postValue(listOf())
            }
        }
    }

    fun getDistanceRecordCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(DistanceRecord::class)
                val list = mutableListOf<Distance>()
                Log.i(TAG, "distance record list size ${response.size}")
                response.forEachIndexed { _, change ->
                    if (change is UpsertionChange) {
                        (change.record as DistanceRecord).apply {
                            val distanceRecord = Distance(
                                startTime.toEpochMilli(),
                                endTime.toEpochMilli(),
                                distance.inMeters.toFloat(),
                                metadata.dataOrigin.packageName
                            )
                            distanceRecord.uuid = metadata.id
                            list.add(distanceRecord)
                        }
                    }
                    distanceList.postValue(list)
                }
            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                distanceList.postValue(listOf())
            }
        }
    }

    fun getSleepSessionRecordCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(SleepSessionRecord::class)
                val list = mutableListOf<SleepSession>()
                response.forEachIndexed { _, change ->
                    Log.i(TAG, "sleep session list size ${response.size}")
                    if (change is UpsertionChange) {
                        (change.record as SleepSessionRecord).apply {
                            val sleepsSession = SleepSession(
                                startTime.toEpochMilli(),
                                endTime.toEpochMilli(),
                                metadata.dataOrigin.packageName
                            )
                            sleepsSession.uuid = metadata.id
                            list.add(sleepsSession)
                        }
                    }
                    sleepSessionList.postValue(list)
                }

            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                sleepSessionList.postValue(listOf())
            }
        }
    }

    fun getSleepStageDataListCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(SleepSessionRecord::class)
                val list = mutableListOf<SleepStage>()
                response.forEachIndexed { _, change ->
                    Log.i(TAG, "Sleep list size ${response.size}")
                    if (change is UpsertionChange) {
                        (change.record as SleepSessionRecord).apply {
                            stages.forEach {
                                val sleepStage = SleepStage(
                                    it.startTime.toEpochMilli(),
                                    it.endTime.toEpochMilli(),
                                    it.stage,
                                    metadata.dataOrigin.packageName
                                )
                                sleepStage.uuid = metadata.id
                                list.add(sleepStage)
                            }
                        }
                    }
                    sleepStageList.postValue(list)
                }

            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                sleepStageList.postValue(listOf())
            }
        }
    }

    fun getBloodPressureDataListCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(BloodPressureRecord::class)
                val list = mutableListOf<BloodPressure>()
                response.forEachIndexed { _, change ->
                    Log.i(TAG, "Blood Pressure list size ${response.size}")
                    if (change is UpsertionChange) {
                        (change.record as BloodPressureRecord).apply {
                            val bloodPressure = BloodPressure(
                                time.toEpochMilli(),
                                diastolic.inMillimetersOfMercury.toFloat(),
                                systolic.inMillimetersOfMercury.toFloat(),
                                metadata.dataOrigin.packageName
                            )
                            bloodPressure.uuid = metadata.id
                            list.add(bloodPressure)
                        }
                    }
                    bloodPressureList.postValue(list)
                }

            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                bloodPressureList.postValue(listOf())
            }
        }
    }

    fun getBloodGlucoseDataListCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(BloodGlucoseRecord::class)
                val list = mutableListOf<BloodGlucose>()
                response.forEachIndexed { _, change ->
                    Log.i(TAG, "Blood Glucose list size ${response.size}")
                    if (change is UpsertionChange) {
                        (change.record as BloodGlucoseRecord).apply {
                            val bloodGlucose = BloodGlucose(
                                time.toEpochMilli(),
                                level.inMillimolesPerLiter.toFloat(),
                                mealType,
                                relationToMeal,
                                metadata.dataOrigin.packageName
                            )
                            bloodGlucose.uuid = metadata.id
                            list.add(bloodGlucose)
                        }
                    }
                    bloodGlucoseList.postValue(list)
                }

            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                bloodGlucoseList.postValue(listOf())
            }
        }
    }

    fun getBloodOxygenDataListCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(OxygenSaturationRecord::class)
                val list = mutableListOf<BloodOxygen>()
                response.forEachIndexed { _, change ->
                    Log.i(TAG, "Blood Oxygen list size ${response.size}")
                    if (change is UpsertionChange) {
                        (change.record as OxygenSaturationRecord).apply {
                            val bloodOxygen = BloodOxygen(
                                time.toEpochMilli(),
                                percentage.value.toFloat(),
                                metadata.dataOrigin.packageName
                            )
                            bloodOxygen.uuid = metadata.id
                            list.add(bloodOxygen)
                        }
                    }
                    bloodOxygenList.postValue(list)
                }

            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                bloodGlucoseList.postValue(listOf())
            }
        }
    }

    fun getHeartRateDataListCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(HeartRateRecord::class)
                val list = mutableListOf<SampleHeartRate>()
                response.forEachIndexed { _, change ->
                    Log.i(TAG, "Heart Rate list size ${response.size}")
                    if (change is UpsertionChange) {
                        (change.record as HeartRateRecord).apply {
                            val hrRate = SampleHeartRate(
                                startTime.toEpochMilli(),
                                samples[0].beatsPerMinute.toFloat(),
                                metadata.dataOrigin.packageName
                            )
                            hrRate.uuid = metadata.id
                            list.add(hrRate)
                        }
                    }
                    sampleHrList.postValue(list)
                }
            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                sampleHrList.postValue(listOf())
            }
        }
    }

    fun getWeightDataListCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(WeightRecord::class)
                val list = mutableListOf<Weight>()
                response.forEachIndexed { _, change ->
                    Log.i(TAG, "weight list size ${response.size}")
                    if (change is UpsertionChange) {
                        (change.record as WeightRecord).apply {
                            val weightItem = Weight(
                                time.toEpochMilli(),
                                weight.inKilograms.toFloat(),
                                metadata.dataOrigin.packageName
                            )
                            weightItem.uuid = metadata.id
                            list.add(weightItem)
                        }
                    }
                    weightList.postValue(list)
                }

            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                weightList.postValue(listOf())
            }
        }
    }

    fun getNutritionDataListCo() {
        viewModelScope.launch {
            try {
                val response = DataSyncManager.getChangesTokenFromHsp(NutritionRecord::class)
                val list = mutableListOf<Nutrition>()
                response.forEachIndexed { _, change ->
                    Log.i(TAG, "nutrition list size ${response.size}")
                    if (change is UpsertionChange) {
                        (change.record as NutritionRecord).apply {
                            val nutritionItem = Nutrition(
                                startTime.toEpochMilli(),
                                energy?.inCalories?: 0.0,
                                totalFat?.inGrams ?: 0.0,
                                saturatedFat?.inGrams ?: 0.0,
                                transFat?.inGrams ?: 0.0,
                                dietaryFiber?.inGrams ?: 0.0,
                                sugar?.inGrams ?: 0.0,
                                protein?.inGrams ?: 0.0,
                                cholesterol?.inMilligrams ?: 0.0,
                                sodium?.inMilligrams ?: 0.0,
                                vitaminA?.inMicrograms ?: 0.0,
                                vitaminC?.inMicrograms ?: 0.0,
                                vitaminD?.inMicrograms ?: 0.0,
                                calcium?.inMilligrams ?: 0.0,
                                iron?.inMilligrams?: 0.0,
                                unsaturatedFat?.inGrams?: 0.0,
                                polyunsaturatedFat?.inGrams ?: 0.0,
                                monounsaturatedFat?.inGrams ?: 0.0,
                                copper?.inGrams ?: 0.0,
                                folicAcid?.inGrams ?: 0.0,
                                iodine?.inGrams ?: 0.0,
                                magnesium?.inGrams ?: 0.0,
                                niacin?.inGrams ?: 0.0,
                                pantothenicAcid?.inGrams ?: 0.0,
                                phosphorus?.inGrams ?: 0.0,
                                riboflavin?.inGrams ?: 0.0,
                                thiamin?.inGrams ?: 0.0,
                                vitaminB12?.inMicrograms ?: 0.0,
                                vitaminB6?.inMicrograms ?: 0.0,
                                vitaminE?.inMicrograms ?: 0.0,
                                zinc?.inMicrograms ?: 0.0,
                                chloride?.inGrams?: 0.0,
                                chromium?.inGrams ?: 0.0,
                                folate?.inGrams ?: 0.0,
                                manganese?.inGrams ?: 0.0,
                                molybdenum?.inGrams ?: 0.0,
                                selenium?.inGrams ?: 0.0,
                                vitaminK?.inGrams ?: 0.0,
                                metadata.dataOrigin.packageName
                            )
                            nutritionItem.uuid = metadata.id
                            list.add(nutritionItem)
                        }
                    }
                    nutritionList.postValue(list)
                }

            } catch (ex: Exception) {
                Log.e(TAG, "exception $ex")
                nutritionList.postValue(listOf())
            }
        }
    }

    fun deleteData(recordType: KClass<out Record>, recordIdsList: List<String>) {
        viewModelScope.launch {
            try {
                HspHealthDataHelper.deleteHealthRecords(recordType, recordIdsList)
                Log.i(TAG, "successfully data deleted")
            } catch (ex: Exception) {
                Log.i(TAG, "exception in data delete $ex")
            }
        }
    }
}