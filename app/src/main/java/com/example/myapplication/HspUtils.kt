package com.example.myapplication

import android.health.connect.datatypes.ExerciseSegmentType
import android.health.connect.datatypes.ExerciseSessionType
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.health.connect.client.records.*
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_BADMINTON
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_BASEBALL
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_BASKETBALL
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_BIKING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_BIKING_STATIONARY
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_BOOT_CAMP
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_BOXING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_CALISTHENICS
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_CRICKET
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_DANCING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_ELLIPTICAL
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_EXERCISE_CLASS
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_FENCING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_FOOTBALL_AMERICAN
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_FRISBEE_DISC
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_GOLF
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_GUIDED_BREATHING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_GYMNASTICS
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_HIKING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_OTHER_WORKOUT
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_PADDLING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_PILATES
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_ROCK_CLIMBING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_ROLLER_HOCKEY
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_ROWING_MACHINE
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_RUGBY
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_RUNNING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_RUNNING_TREADMILL
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_SKATING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_SKIING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_SQUASH
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_STAIR_CLIMBING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_STAIR_CLIMBING_MACHINE
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_STRENGTH_TRAINING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_SURFING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_SWIMMING_OPEN_WATER
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_SWIMMING_POOL
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_WALKING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_WEIGHTLIFTING
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_WHEELCHAIR
import androidx.health.connect.client.records.ExerciseSessionRecord.Companion.EXERCISE_TYPE_YOGA
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.KClass

object HspUtils {

    const val HOURS_IN_DAY: Byte = 24

    const val LAST_HOUR_IN_DAY: Byte = (HOURS_IN_DAY - 1.toByte()).toByte()
    /**
     * Amount minutes in one hour.
     */
    const val MINUTES_IN_HOUR: Byte = 60

    /**
     * Value of last minute in hour.
     */
    const val LAST_MINUTE_IN_HOUR = (MINUTES_IN_HOUR - 1.toByte()).toByte()

    /**
     * Amount seconds in one minute.
     */
    const val SECONDS_IN_MINUTE: Byte = 60

    /**
     * Value of last second of minute.
     */
    const val LAST_SECOND_IN_MINUTE = (SECONDS_IN_MINUTE - 1.toByte()).toByte()

    /**
     * Amount milliseconds in one second.
     */
    const val MILLIS_IN_SECOND = 1000

    val LAST_MILLI_IN_SECOND: Int = MILLIS_IN_SECOND - 1


    fun getDataPermissionList(): List<KClass<out Record>> =
        mutableListOf(
            StepsRecord::class,
            ExerciseSessionRecord::class,
            TotalCaloriesBurnedRecord::class,
            DistanceRecord::class,
            Vo2MaxRecord::class,
            PowerRecord::class,
            SpeedRecord::class,
            HeartRateRecord::class,
            StepsCadenceRecord::class,
            CyclingPedalingCadenceRecord::class,
            SleepSessionRecord::class,
            SleepStageRecord::class,
            NutritionRecord::class,
            BloodGlucoseRecord::class,
            BloodPressureRecord::class,
            OxygenSaturationRecord::class,
            WeightRecord::class,
            HeightRecord::class,
            BodyFatRecord::class,
            BasalMetabolicRateRecord::class
        )
    
    fun getMealTypeForHsp(mealType: Int): Int {
        return when (mealType) {
            0 -> MealType.MEAL_TYPE_BREAKFAST
            1 -> MealType.MEAL_TYPE_LUNCH
            2 -> MealType.MEAL_TYPE_DINNER
            3 -> MealType.MEAL_TYPE_SNACK
            else -> MealType.MEAL_TYPE_UNKNOWN
        }
    }

    @JvmStatic
    fun getRelationToMealFoHsp(mealType: Int): Int {
        return when (mealType) {
            0 -> BloodGlucoseRecord.RELATION_TO_MEAL_BEFORE_MEAL
            1 -> BloodGlucoseRecord.RELATION_TO_MEAL_AFTER_MEAL
            2-> BloodGlucoseRecord.RELATION_TO_MEAL_FASTING
            else -> BloodGlucoseRecord.RELATION_TO_MEAL_GENERAL
        }
    }

    fun getSpecimenSource(type: Int): Int {
        return when(type) {
            0 -> BloodGlucoseRecord.SPECIMEN_SOURCE_WHOLE_BLOOD
            1 -> BloodGlucoseRecord.SPECIMEN_SOURCE_PLASMA
            2 -> BloodGlucoseRecord.SPECIMEN_SOURCE_SERUM
            else -> BloodGlucoseRecord.SPECIMEN_SOURCE_UNKNOWN
        }
    }

    fun getActivityTypeForHsp(type: Int): Int {
        Log.i("HspUtils", "type $type" )
        return when (type) {
            0 -> EXERCISE_TYPE_OTHER_WORKOUT
            1 -> EXERCISE_TYPE_BADMINTON
            2 -> EXERCISE_TYPE_BASEBALL
            3 -> EXERCISE_TYPE_BASKETBALL
            4 -> EXERCISE_TYPE_BIKING
            5 -> EXERCISE_TYPE_BIKING_STATIONARY
            6 -> EXERCISE_TYPE_BOOT_CAMP
            7 -> EXERCISE_TYPE_BOXING
            8 -> EXERCISE_TYPE_CALISTHENICS
            9 -> EXERCISE_TYPE_CRICKET
            10 -> EXERCISE_TYPE_DANCING
            11 -> EXERCISE_TYPE_ELLIPTICAL
            12 -> EXERCISE_TYPE_EXERCISE_CLASS
            13 -> EXERCISE_TYPE_FENCING
            14 -> EXERCISE_TYPE_FOOTBALL_AMERICAN
            15 -> EXERCISE_TYPE_FRISBEE_DISC
            16 -> EXERCISE_TYPE_GOLF
            17 -> EXERCISE_TYPE_GUIDED_BREATHING
            18 -> EXERCISE_TYPE_GYMNASTICS
            19 -> EXERCISE_TYPE_PADDLING
            20 -> EXERCISE_TYPE_ROCK_CLIMBING
            21 -> EXERCISE_TYPE_ROLLER_HOCKEY
            22 -> EXERCISE_TYPE_PILATES
            23 -> EXERCISE_TYPE_RUGBY
            24 -> EXERCISE_TYPE_SKATING
            25 -> EXERCISE_TYPE_SKIING
            26 -> EXERCISE_TYPE_SQUASH
            else -> EXERCISE_TYPE_SURFING
        }
    }

    @RequiresApi(34)
    fun getExerciseSessionTypeForHsp(type: Int): Int {
        Log.i("HspUtils", "type $type" )
        return when (type) {
            0 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_OTHER_WORKOUT
            1 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_BADMINTON
            2 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_BASEBALL
            3 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_BASKETBALL
            4 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_BIKING
            5 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_BIKING_STATIONARY
            6 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_BOOT_CAMP
            7 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_BOXING
            8 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_CALISTHENICS
            9 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_CRICKET
            10 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_DANCING
            11 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_ELLIPTICAL
            12 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_EXERCISE_CLASS
            13 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_FENCING
            14 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_FOOTBALL_AMERICAN
            15 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_FRISBEE_DISC
            16 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_GOLF
            17 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_GUIDED_BREATHING
            18 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_GYMNASTICS
            19 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_PADDLING
            20 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_ROCK_CLIMBING
            21 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_ROLLER_HOCKEY
            22 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_PILATES
            23 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_RUGBY
            24 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_SKATING
            25 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_SKIING
            26 -> ExerciseSessionType.EXERCISE_SESSION_TYPE_SQUASH
            else -> ExerciseSessionType.EXERCISE_SESSION_TYPE_SURFING
        }
    }

    @RequiresApi(34)
    fun getExerciseSegmentType(exerciseTypes: Int, type: Int): Int {
        return when(exerciseTypes) {
            EXERCISE_TYPE_BIKING -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_BIKING
            EXERCISE_TYPE_BIKING_STATIONARY -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_BIKING_STATIONARY
            EXERCISE_TYPE_ELLIPTICAL -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_ELLIPTICAL
            EXERCISE_TYPE_EXERCISE_CLASS -> when(type) {
                0 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_YOGA
                1 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_BIKING_STATIONARY
                2 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_PILATES
                else -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_HIGH_INTENSITY_INTERVAL_TRAINING
            }
            EXERCISE_TYPE_GYMNASTICS -> getExerciseSegment(type)
            EXERCISE_TYPE_HIKING -> when(type) {
                0 -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_WALKING
                else -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_WHEELCHAIR
            }
            EXERCISE_TYPE_PILATES -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_PILATES
            EXERCISE_TYPE_ROWING_MACHINE -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_ROWING_MACHINE
            EXERCISE_TYPE_RUNNING -> when(type) {
                0 -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_RUNNING
                else -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_WALKING
            }
            EXERCISE_TYPE_RUNNING_TREADMILL -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_RUNNING_TREADMILL
            EXERCISE_TYPE_STRENGTH_TRAINING -> getExerciseSegment(type)
            EXERCISE_TYPE_STAIR_CLIMBING -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_STAIR_CLIMBING
            EXERCISE_TYPE_STAIR_CLIMBING_MACHINE -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_STAIR_CLIMBING_MACHINE
            EXERCISE_TYPE_SWIMMING_OPEN_WATER -> when(type) {
                0 -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_SWIMMING_OPEN_WATER
                else -> getSwimmingSegment(type)
            }
            EXERCISE_TYPE_SWIMMING_POOL -> when(type) {
                0 -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_SWIMMING_POOL
                else -> getSwimmingSegment(type)
            }
            EXERCISE_TYPE_WALKING -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_WALKING
            EXERCISE_TYPE_WHEELCHAIR -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_WHEELCHAIR
            EXERCISE_TYPE_WEIGHTLIFTING -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_WEIGHTLIFTING
            EXERCISE_TYPE_YOGA -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_YOGA
            else -> ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_UNKNOWN
        }
    }

    private fun getSwimmingSegment(type: Int): Int {
        return when(type) {
            1 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SWIMMING_BACKSTROKE
            2 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SWIMMING_BREASTSTROKE
            3 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SWIMMING_FREESTYLE
            4 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SWIMMING_BUTTERFLY
            5 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SWIMMING_MIXED
            else -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SWIMMING_OTHER
        }
    }
    private fun getExerciseSegment(type: Int): Int {
        return when(type) {
            0 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_ARM_CURL
            1 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_BACK_EXTENSION
            2  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_BALL_SLAM
            3  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_BARBELL_SHOULDER_PRESS
            4  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_BENCH_PRESS
            5  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_BENCH_SIT_UP
            6  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_BURPEE
            7  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_CRUNCH
            8  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DEADLIFT
            9  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DOUBLE_ARM_TRICEPS_EXTENSION
            10  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DUMBBELL_CURL_LEFT_ARM
            11  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DUMBBELL_CURL_RIGHT_ARM
            12 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DUMBBELL_FRONT_RAISE
            13 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DUMBBELL_LATERAL_RAISE
            14 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DUMBBELL_ROW
            15 -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DUMBBELL_TRICEPS_EXTENSION_LEFT_ARM
            16  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DUMBBELL_TRICEPS_EXTENSION_RIGHT_ARM
            17  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_DUMBBELL_TRICEPS_EXTENSION_TWO_ARM
            18  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_FORWARD_TWIST
            19  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_FRONT_RAISE
            20  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_HIP_THRUST
            21  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_HULA_HOOP
            22  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_JUMP_ROPE
            23  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_JUMPING_JACK
            24  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_KETTLEBELL_SWING
            25  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_LATERAL_RAISE
            26  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_LAT_PULL_DOWN
            27  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_LEG_CURL
            28  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_LEG_EXTENSION
            29  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_LEG_PRESS
            30  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_LEG_RAISE
            31  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_LUNGE
            32  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_MOUNTAIN_CLIMBER
            33  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_PLANK
            34  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_PULL_UP
            35  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_PUNCH
            36  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SHOULDER_PRESS
            37  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SINGLE_ARM_TRICEPS_EXTENSION
            38  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SIT_UP
            39  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_SQUAT
            40  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_UPPER_TWIST
            else  -> ExerciseSegment.EXERCISE_SEGMENT_TYPE_WEIGHTLIFTING
        }
    }
    @JvmStatic
    fun getSleepStageToHsp(stage: Int): Int {
        return when (stage) {
            0 -> SleepStageRecord.STAGE_TYPE_AWAKE
            1 -> SleepStageRecord.STAGE_TYPE_LIGHT
            2 -> SleepStageRecord.STAGE_TYPE_DEEP
            3 -> SleepStageRecord.STAGE_TYPE_REM
            else -> SleepStageRecord.STAGE_TYPE_UNKNOWN
        }
    }

    @JvmStatic
    fun getDateTime(localTime: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd(EEE) HH:mm:ss")
        return sdf.format(localTime)
    }

    @JvmStatic
    fun getStartOfDay(time: Long): Long {
        val tempCal: Calendar = Calendar.getInstance()
        run {
            tempCal.timeInMillis = time
            tempCal[Calendar.HOUR_OF_DAY] = 0
            tempCal[Calendar.MINUTE] = 0
            tempCal[Calendar.SECOND] = 0
            tempCal[Calendar.MILLISECOND] = 0
            return tempCal.timeInMillis
        }
    }

    @JvmStatic
    fun getEndOfDay(time: Long): Long {
        val tempCal: Calendar = Calendar.getInstance()
        tempCal.timeInMillis = time
        tempCal[Calendar.HOUR_OF_DAY] = LAST_HOUR_IN_DAY.toInt()
        tempCal[Calendar.MINUTE] = LAST_MINUTE_IN_HOUR.toInt()
        tempCal[Calendar.SECOND] = LAST_SECOND_IN_MINUTE.toInt()
        tempCal[Calendar.MILLISECOND] = LAST_MILLI_IN_SECOND
        return tempCal.timeInMillis
    }


    @JvmStatic
    fun getDataype(itemType: Int): KClass<out Record> {
        return when (itemType) {
            HspConstants.ItemTypes.ActivitySession -> ExerciseSessionRecord::class

            HspConstants.ItemTypes.SleepSession -> SleepSessionRecord::class
            HspConstants.ItemTypes.SleepStage -> SleepStageRecord::class

            HspConstants.ItemTypes.BloodGlucose -> BloodGlucoseRecord::class
            HspConstants.ItemTypes.BloodPressure -> BloodPressureRecord::class
            HspConstants.ItemTypes.BloodOxygen -> OxygenSaturationRecord::class
            HspConstants.ItemTypes.HeartRate -> HeartRateRecord::class
            HspConstants.ItemTypes.Weight -> WeightRecord::class
            HspConstants.ItemTypes.Nutrition -> NutritionRecord::class
            else -> BloodPressureRecord::class
        }
    }

    @JvmStatic
    fun getHspDataTypesForPermissionOfHsp(recordType: KClass<out Record>): List<KClass<out Record>> {
        val list = mutableListOf<KClass<out Record>>()
        when (recordType) {
            StepsRecord::class -> {
                list.add(StepsRecord::class)
            }
            ExerciseSessionRecord::class -> {
                list.add(ExerciseSessionRecord::class)
                list.add(DistanceRecord::class)
                list.add(Vo2MaxRecord::class)
            }
            SleepSessionRecord::class-> {
                list.add(SleepSessionRecord::class)
                list.add(SleepStageRecord::class)
            }
            HeartRateRecord::class -> {
                list.add(HeartRateRecord::class)
            }
            BloodPressureRecord::class -> {
                list.add(BloodPressureRecord::class)
            }
            BloodGlucoseRecord::class -> {
                list.add(BloodGlucoseRecord::class)
            }
            OxygenSaturationRecord::class -> {
                list.add(OxygenSaturationRecord::class)
            }
            WeightRecord::class -> {
                list.add(WeightRecord::class)
                list.add(HeightRecord::class)
                list.add(BodyFatRecord::class)
                list.add(BasalMetabolicRateRecord::class)
            }
            NutritionRecord::class -> {
                list.add(NutritionRecord::class)
            }
        }
        return list
    }

    @JvmStatic
    fun getHspDataTypeListForFullSync():MutableMap<String, KClass<out Record>> =
        hashMapOf(
            ExerciseSessionRecord::class.toString() to ExerciseSessionRecord::class,
            SleepSessionRecord::class.toString() to SleepSessionRecord::class,
            HeartRateRecord::class.toString() to HeartRateRecord::class,
            BloodPressureRecord::class.toString() to BloodPressureRecord::class,
            BloodGlucoseRecord::class.toString() to BloodGlucoseRecord::class,
            OxygenSaturationRecord::class.toString() to OxygenSaturationRecord::class,
            WeightRecord::class.toString() to WeightRecord::class,
            NutritionRecord::class.toString() to NutritionRecord::class
        )

    fun getRecordTypeListForSync(): List<KClass<out Record>> =
        mutableListOf(
            BloodPressureRecord::class,
            BloodGlucoseRecord::class,
            ExerciseSessionRecord::class,
            NutritionRecord::class,
            OxygenSaturationRecord::class,
            HeartRateRecord::class,
            WeightRecord::class,
            SleepSessionRecord::class,
            DistanceRecord::class,
            TotalCaloriesBurnedRecord::class
        )
}
