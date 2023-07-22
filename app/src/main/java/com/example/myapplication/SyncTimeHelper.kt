package com.example.myapplication

import android.content.Context
import androidx.health.connect.client.records.*
import kotlin.reflect.KClass


object SyncTimeHelper {
    @JvmStatic
    fun getSyncToken(recordType: KClass<out Record>): String? {
        val sp = ContextHolder.getContext().getSharedPreferences("SyncToken", Context.MODE_PRIVATE)
        return when(recordType) {
            ExerciseSessionRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_ACTIVITY_SESSION_OF_HSP, null)
            SleepSessionRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_SLEEP_SESSION_OF_HSP, null)
            NutritionRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_NUTRITION_OF_HSP, null)
            HeartRateRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_HR_OF_HSP, null)
            BloodPressureRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_BP_OF_HSP, null)
            BloodGlucoseRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_BG_OF_HSP, null)
            OxygenSaturationRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_BO_OF_HSP, null)
            WeightRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_WEIGHT_OF_HSP, null)
            DistanceRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_DISTANCE_OF_HSP, null)
            TotalCaloriesBurnedRecord::class -> sp.getString(HspConstants.SyncToken.SYNC_TOKEN_TOTAL_ENERGY_OF_HSP, null)
            else -> null
        }
    }

    @JvmStatic
    fun setSyncToken(recordType: KClass<out Record>, syncToken: String) {
        val sp = ContextHolder.getContext().getSharedPreferences("SyncToken", Context.MODE_PRIVATE)
        when(recordType) {
            ExerciseSessionRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_ACTIVITY_SESSION_OF_HSP, syncToken).apply()
            SleepSessionRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_SLEEP_SESSION_OF_HSP, syncToken).apply()
            NutritionRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_NUTRITION_OF_HSP, syncToken).apply()
            HeartRateRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_HR_OF_HSP, syncToken).apply()
            BloodPressureRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_BP_OF_HSP, syncToken).apply()
            BloodGlucoseRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_BG_OF_HSP, syncToken).apply()
            OxygenSaturationRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_BO_OF_HSP, syncToken).apply()
            WeightRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_WEIGHT_OF_HSP, syncToken).apply()
            DistanceRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_DISTANCE_OF_HSP, syncToken).apply()
            TotalCaloriesBurnedRecord::class -> sp.edit().putString(HspConstants.SyncToken.SYNC_TOKEN_TOTAL_ENERGY_OF_HSP, syncToken).apply()
        }
    }
}