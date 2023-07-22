package com.example.myapplication.itemview

import android.content.Context
import android.widget.LinearLayout
import com.example.myapplication.HspConstants
import com.example.myapplication.item.*

object ItemFactory {

    @JvmStatic
    fun getItem(context: Context, itemType: Int, healthData: HealthData): LinearLayout? {

        return when(itemType) {
            HspConstants.ItemTypes.ActivitySession -> ActivitySessionView(context, healthData as ActivitySession)
            HspConstants.ItemTypes.TotalEnergyBurned -> TotalEnergyBurnedView(context, healthData as TotalEnergyBurned)
            HspConstants.ItemTypes.Distance -> DistanceView(context, healthData as Distance)

            HspConstants.ItemTypes.SleepSession -> SleepSessionView(context, healthData as SleepSession)
            HspConstants.ItemTypes.SleepStage -> SleepStageView(context, healthData as SleepStage)
            HspConstants.ItemTypes.BloodPressure -> BloodPressureView(context, healthData as BloodPressure)
            HspConstants.ItemTypes.BloodGlucose -> BloodGlucoseView(context, healthData as BloodGlucose)
            HspConstants.ItemTypes.BloodOxygen -> BloodOxygenView(context, healthData as BloodOxygen)
            HspConstants.ItemTypes.Water -> WaterView(context, healthData as Water)
            HspConstants.ItemTypes.Weight -> WeightView(context, healthData as Weight)
            HspConstants.ItemTypes.HeartRate -> SampleHeartRateView(context, healthData as SampleHeartRate)
            HspConstants.ItemTypes.Height -> HeightView(context, healthData as Height)
            HspConstants.ItemTypes.BodyFat -> BodyFatView(context, healthData as BodyFat)
            HspConstants.ItemTypes.BasalRate -> BasalRateView(context, healthData as BasalRate)
            HspConstants.ItemTypes.Nutrition -> NutritionView(context, healthData as Nutrition)

            HspConstants.ItemTypes.SeriesHeartRate,
            HspConstants.ItemTypes.Speed,
            HspConstants.ItemTypes.Power,
            HspConstants.ItemTypes.Cadence
            -> SeriesDataView(context, healthData as SeriesData)
            else -> null
        }

    }
}