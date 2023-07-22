package com.example.myapplication

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.health.connect.client.records.*
import androidx.health.connect.client.records.metadata.Metadata
import androidx.health.connect.client.records.metadata.DataOrigin
import androidx.health.connect.client.units.*
import java.time.Instant
import java.util.*

object DataGenerator {
    private const val TAG = "HSPTEST-DataGenerator"

    private const val maxSize = 10
    private const val seriesMaxSize = 100

    private const val SECOND = 1000
    private const val MINUTE = 60 * SECOND
    private const val HALP_HOUR = 30 * MINUTE
    private const val HOUR = 60 * MINUTE
    private const val DAY = 24 * HOUR

    fun getExerciseRecordList(): List<Record> {
        var endTime = System.currentTimeMillis() - DAY*(maxSize/2)
        val startTime = endTime - HOUR
        val list = mutableListOf<Record>()
        for(i in 1..maxSize/2) {
            list.apply {
                add(getActivitySessionRecord(startTime, endTime))
                add(getTotalEnergyBurnedRecord(startTime, endTime))
                add(getDistanceIntervalRecord(startTime, endTime))
                add(getVo2MaxSampleRecord(startTime))
                add(getCycleCadenceRecord(startTime, endTime))
                add(getHeartRateSeriesRecord(startTime, endTime))
                add(getSpeedSeriesRecord(startTime, endTime))
                add(getSeriesPowerRecord(startTime, endTime))
            }
            endTime += HOUR*12
        }
        return list
    }

    fun getExerciseRecord(): List<Record> {
        val endTime = System.currentTimeMillis() - DAY*(maxSize/2)
        val startTime = endTime - HOUR
        val list = mutableListOf<Record>()
        list.apply {
            add(getActivitySessionRecord(startTime, endTime))
            add(getTotalEnergyBurnedRecord(startTime, endTime))
            add(getDistanceIntervalRecord(startTime, endTime))
            add(getVo2MaxSampleRecord(startTime))
            add(getCycleCadenceRecord(startTime, endTime))
            add(getHeartRateSeriesRecord(startTime, endTime))
            add(getSpeedSeriesRecord(startTime, endTime))
            add(getSeriesPowerRecord(startTime, endTime))
        }
        return list
    }

    private fun getActivitySessionRecord(startTime: Long, endTime: Long): ExerciseSessionRecord {
        val exerciseType = HspUtils.getActivityTypeForHsp((0..30).random())
        Log.i(TAG, "getActivitySessionRecord")
        return ExerciseSessionRecord(
            Instant.ofEpochMilli(startTime),
            null,
            Instant.ofEpochMilli(endTime),
            null,
            exerciseType,
            null,
            null,
            getMetaData(ContextHolder.getContext().packageName, null),
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                getExerciseSegmentType(exerciseType, startTime, endTime)
            } else {
                emptyList()
            }
        )
    }

    @RequiresApi(34)
    private fun getExerciseSegmentType(exerciseTypes: Int, startTime: Long, endTime: Long): List<ExerciseSegment> {
        val segmentTime = (endTime - startTime) / 5
        var start = startTime
        val segmentList = mutableListOf<ExerciseSegment>()
        while (start < endTime) {
            val tempEndTime = start + segmentTime
            ExerciseSegment(Instant.ofEpochMilli(start),
                Instant.ofEpochMilli(tempEndTime),
                HspUtils.getExerciseSegmentType(exerciseTypes, (0..30).random())).let {
                segmentList.add(it)
            }
            start += segmentTime
        }
        Log.i(TAG, "segment list size ${segmentList.size}")
        return segmentList
    }

    private fun getTotalEnergyBurnedRecord(startTime: Long, endTime: Long) :TotalCaloriesBurnedRecord {
        return TotalCaloriesBurnedRecord(
            Instant.ofEpochMilli(startTime),
            null,
            Instant.ofEpochMilli(endTime),
null,
            Energy.calories((50..100).random().toDouble())
        )
    }

    private fun getDistanceIntervalRecord(startTime: Long, endTime: Long):DistanceRecord {
        Log.i(TAG, "getDistanceIntervalRecord")
        return DistanceRecord(
            Instant.ofEpochMilli(startTime),
            null,
            Instant.ofEpochMilli(endTime),
            null,
            Length.meters((50..100).random().toDouble())
        )
    }

    private fun getVo2MaxSampleRecord(startTime: Long):Vo2MaxRecord {
        Log.d(TAG, "getVo2Max")
        return Vo2MaxRecord(
            Instant.ofEpochMilli(startTime),
            null,
            (80..100).random().toDouble(),
            Vo2MaxRecord.MEASUREMENT_METHOD_OTHER
        )
    }

    private fun getSpeedSeriesRecord(startTime: Long, endTime: Long): SpeedRecord {
        val speedList = mutableListOf<SpeedRecord.Sample>()
        var time = startTime
        while(time < endTime) {
            val sample =SpeedRecord.Sample(Instant.ofEpochMilli(time),
                Velocity.kilometersPerHour((30..35).random().toDouble()))
            speedList.add(sample)
            time += SECOND
        }
        return SpeedRecord(
            Instant.ofEpochMilli(startTime),
            null,
            Instant.ofEpochMilli(endTime),
            null,
            speedList
        )
    }

    private fun getSeriesPowerRecord(startTime: Long, endTime: Long):PowerRecord {
        val powerList = mutableListOf<PowerRecord.Sample>()
        var time = startTime
        while(time < endTime) {
            val sample =PowerRecord.Sample(Instant.ofEpochMilli(time),
                Power.watts((30..35).random().toDouble()))
            powerList.add(sample)
            time += SECOND
        }
        return PowerRecord(
            Instant.ofEpochMilli(startTime),
            null,
            Instant.ofEpochMilli(endTime),
            null,
            powerList
        )
    }

    private fun getHeartRateSeriesRecord(startTime: Long, endTime: Long):HeartRateRecord {
        val heartRateList = mutableListOf<HeartRateRecord.Sample>()
        var time = startTime
        while(time < endTime) {
            val sample = HeartRateRecord.Sample(Instant.ofEpochMilli(time), (70..85).random().toLong())
            heartRateList.add(sample)
            time += SECOND
        }
        return HeartRateRecord(
            Instant.ofEpochMilli(startTime),
            null,
            Instant.ofEpochMilli(endTime),
            null,
            heartRateList
        )
    }

    private fun getStepCadenceRecord(startTime: Long, endTime: Long): StepsCadenceRecord {
        val cadenceList = mutableListOf<StepsCadenceRecord.Sample>()
        var time = startTime
        while(time < endTime) {
            val sample = StepsCadenceRecord.Sample(Instant.ofEpochMilli(time), (30..35).random().toDouble())
            cadenceList.add(sample)
            time += SECOND
        }
        return StepsCadenceRecord(Instant.ofEpochMilli(startTime),
            null,
        Instant.ofEpochMilli(endTime),
            null,
            cadenceList
        )
    }

    private fun getCycleCadenceRecord(startTime: Long, endTime: Long): CyclingPedalingCadenceRecord {
        val cadenceRecord = mutableListOf<CyclingPedalingCadenceRecord.Sample>()
        var time = startTime
        while (time < endTime) {
            val sample = CyclingPedalingCadenceRecord.Sample(Instant.ofEpochMilli(time), (30..35).random().toDouble())
            cadenceRecord.add(sample)
            time += SECOND
        }
        return CyclingPedalingCadenceRecord(Instant.ofEpochMilli(startTime),
            null,
            Instant.ofEpochMilli(endTime),
            null,
            cadenceRecord
        )
    }

    fun getSleepListRecord(): List<Record> {
        var startTime = HspUtils.getStartOfDay(System.currentTimeMillis() - DAY*maxSize) + HOUR*(21..23).random()
        val list = mutableListOf<Record>()

        for(i in 1..maxSize) {
            val endTime = startTime + HOUR*(7..9).random()
            Log.i(TAG, "getSleepList - start time : $startTime , end time : $endTime")
            list.add(getSleepSessionRecord(startTime, endTime))
            startTime += DAY
        }
        return list
    }

    fun getSleepRecord(): List<Record> {
        val endTime = System.currentTimeMillis()
        val startTime = endTime - HOUR*6

        val list = mutableListOf<Record>()
        list.add(getSleepSessionRecord(startTime, endTime))
        return list
    }

    private fun getSleepSessionRecord(startTime: Long, endTime: Long): SleepSessionRecord {
        return SleepSessionRecord(
            Instant.ofEpochMilli(startTime),
            null,
            Instant.ofEpochMilli(endTime),
            null,
            null,
            null,
            getSleepStage(startTime, endTime),
            getMetaData(ContextHolder.getContext().packageName, null)
        )
    }

    private fun getSleepStage(startTime: Long, endTime: Long): List<SleepSessionRecord.Stage> {
        return listOf(
            SleepSessionRecord.Stage(Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(endTime), HspUtils.getSleepStageToHsp((0..5).random()))
        )
    }

    private fun getSleepStageRecord(startTime: Long, endTime: Long): SleepStageRecord {
        return SleepStageRecord(
            Instant.ofEpochMilli(startTime),
            null,
            Instant.ofEpochMilli(endTime),
            null,
            HspUtils.getSleepStageToHsp((0..5).random())
        )
    }


    fun getBloodPressureRecord(): List<Record> {
        return listOf(BloodPressureRecord(
            Instant.ofEpochMilli(System.currentTimeMillis()),
            null,
            Pressure.millimetersOfMercury((100..130).random().toDouble()),
            Pressure.millimetersOfMercury((80..90).random().toDouble()),
            BloodPressureRecord.BODY_POSITION_UNKNOWN,
            BloodPressureRecord.MEASUREMENT_LOCATION_UNKNOWN,
            getMetaData(ContextHolder.getContext().packageName, null)
        ))
    }

    fun getBloodPressureListRecord(): List<Record> {
        val time = System.currentTimeMillis() - HOUR
        val list = mutableListOf<Record>()
        for(i in 1..maxSize) {
            list.add(BloodPressureRecord(
                Instant.ofEpochMilli(time + i* MINUTE*5),
                null,
                Pressure.millimetersOfMercury((100..130).random().toDouble()),
                Pressure.millimetersOfMercury((80..90).random().toDouble()),
                BloodPressureRecord.BODY_POSITION_UNKNOWN,
                BloodPressureRecord.MEASUREMENT_LOCATION_UNKNOWN,
                getMetaData(ContextHolder.getContext().packageName, null)
            ))
        }
        return list
    }

    fun getBloodGlucoseRecord(): List<Record> {
        val list = mutableListOf<Record>()
        list.add(BloodGlucoseRecord(
            Instant.ofEpochMilli(System.currentTimeMillis()),
            null,
            BloodGlucose.milligramsPerDeciliter((5..8).random().toDouble()),
            HspUtils.getSpecimenSource((0..3).random()),
            HspUtils.getMealTypeForHsp((0..4).random()),
            HspUtils.getRelationToMealFoHsp((0..3).random()),
            getMetaData(ContextHolder.getContext().packageName, null)
        ))
        return list
    }

    fun getBloodGlucoseListRecord(): List<Record> {
        val time = System.currentTimeMillis() - HOUR
        val list = mutableListOf<Record>()
        for(i in 1..maxSize) {
            list.add(BloodGlucoseRecord(
                Instant.ofEpochMilli(time + i* MINUTE*5),
                null,
                BloodGlucose.milligramsPerDeciliter((5..8).random().toDouble()),
                HspUtils.getSpecimenSource((0..3).random()),
                HspUtils.getMealTypeForHsp((0..4).random()),
                HspUtils.getRelationToMealFoHsp((0..3).random()),
                getMetaData(ContextHolder.getContext().packageName, null)
            ))
        }
        return list
    }

    fun getSeriesHeartRateRecord(): List<Record> {
        val startTime = System.currentTimeMillis()
        val endTime = startTime*seriesMaxSize*1000
        val sampleList = mutableListOf<HeartRateRecord.Sample>()
        sampleList.add(HeartRateRecord.Sample(Instant.ofEpochMilli(startTime), (65..90).random().toLong()))
        for(i in 0..seriesMaxSize) {
            sampleList.add(HeartRateRecord.Sample(Instant.ofEpochMilli(startTime + 100*i), (65..90).random().toLong()))
        }
        return listOf(
            HeartRateRecord(
                Instant.ofEpochMilli(startTime),
                null,
                Instant.ofEpochMilli(endTime),
                null,
                sampleList,
                getMetaData(ContextHolder.getContext().packageName, null)
            )
        )
    }

    fun getOxygenSaturationRecord(): List<Record> {
        val time = System.currentTimeMillis()
        return listOf(
            OxygenSaturationRecord(
                Instant.ofEpochMilli(time),
                null,
                Percentage((90..99).random().toDouble()),
                getMetaData(ContextHolder.getContext().packageName, null)
            )
        )
    }

    fun getOxygenSaturationRecordList(): List<Record> {
        val time = System.currentTimeMillis()
        val list = mutableListOf<Record>()
        for (i in 1..maxSize) {
           list.add(
               OxygenSaturationRecord(
                   Instant.ofEpochMilli(time + i* MINUTE*5),
                   null,
                   Percentage((90..99).random().toDouble()),
                   getMetaData(ContextHolder.getContext().packageName, null)
               )
           )
        }
        return list
    }

    fun getWeightRecord(): List<Record> {
        return listOf(WeightRecord(
            Instant.ofEpochMilli(System.currentTimeMillis()),
            null,
            Mass.kilograms((75..80).random().toDouble()),
            getMetaData(ContextHolder.getContext().packageName, null)
        ))
    }

    fun getWeightListRecord(): List<Record> {
        val list = mutableListOf<Record>()
        val time = System.currentTimeMillis() - HOUR
        for(i in 1..maxSize) {
            list.add(
                WeightRecord(
                    Instant.ofEpochMilli(time + i* MINUTE*5),
                    null,
                    Mass.kilograms((75..80).random().toDouble()),
                    getMetaData(ContextHolder.getContext().packageName, null)
                )
            )
        }
        return list
    }

    fun getHydrationRecord(): List<Record> {
        val startTime = System.currentTimeMillis()
        val endTime = startTime +1000
        return listOf(
              HydrationRecord(
                  Instant.ofEpochMilli(startTime),
                  null,
                  Instant.ofEpochMilli(endTime),
                  null,
                  Volume.liters((1..2).random().toDouble()),
                  getMetaData(ContextHolder.getContext().packageName, null)
              )
          )
    }

    fun getHydrationListRecord(): List<Record> {
        val list = mutableListOf<Record>()
        var startTime = System.currentTimeMillis() - HOUR
        for(i in 1..maxSize) {
            list.add(
                HydrationRecord(
                    Instant.ofEpochMilli(startTime),
                    null,
                    Instant.ofEpochMilli(startTime + 1000),
                    null,
                    Volume.liters((1..2).random().toDouble()),
                    getMetaData(ContextHolder.getContext().packageName, null)
                )
            )
            startTime += MINUTE*10
        }
        return list
    }

    fun getNutritionRecord(): List<Record> {
        val startTime = System.currentTimeMillis()
        val endTime = startTime + 1000
        return listOf(
            NutritionRecord(
                Instant.ofEpochMilli(startTime),
                null,
                Instant.ofEpochMilli(endTime),
                null,
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Energy.kilocalories((0..100000).random().toDouble()),
                Energy.kilocalories((0..100000).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100000).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                Mass.grams((0..100).random().toDouble()),
                null,
                HspUtils.getMealTypeForHsp((0..4).random()),
                getMetaData(ContextHolder.getContext().packageName, null)
            )
        )
    }

    private fun getDataOrigin(pkgName: String) : DataOrigin =
        DataOrigin(pkgName)

    private fun getMetaData(pkgName: String, hspUuid: String?): Metadata {
        return Metadata(
            hspUuid ?: "",
            getDataOrigin(pkgName),
            Instant.EPOCH,
            null,
            0,
            null
        )
    }
}