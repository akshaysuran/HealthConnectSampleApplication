package com.example.myapplication

object HspConstants {

    const val CLIENT_NAME = "MyApplication"
    const val PACKAGE_NAME = "com.samsung.android.service.health"
    const val ACTION_SAMSUNG_PROVIDER_SERVICE_BIND = "com.samsung.android.service.health.data.hsp.BIND"


    object ItemTypes {
        const val  BloodPressure = 10001
        const val  BloodGlucose  = 10002
        const val  BloodOxygen  = 10003
        const val  Water  = 10004
        const val  Weight  = 10005
        const val  SleepSession  = 10006
        const val  SleepStage  = 10007
        const val  HeartRate  = 10008
        const val  SeriesHeartRate  = 10009
        const val  Height  = 10010
        const val  BodyFat  = 10011
        const val  BasalRate  = 10012
        const val  Nutrition  = 10014

        const val  ActivitySession  =20001
        const val  TotalEnergyBurned  = 20002
        const val  Distance  = 20003

        const val  Speed = 20005
        const val  Power = 20006
        const val  Cadence = 20007
    }

    object SyncToken {
        const val SYNC_TOKEN_ACTIVITY_SESSION_OF_HSP = "sync_token_hsp_a_session"
        const val SYNC_TOKEN_SLEEP_SESSION_OF_HSP = "sync_token_hsp_s_session"
        const val SYNC_TOKEN_HR_OF_HSP = "sync_token_hsp_hr"
        const val SYNC_TOKEN_BP_OF_HSP = "sync_token_hsp_bp"
        const val SYNC_TOKEN_BG_OF_HSP = "sync_token_hsp_bg"
        const val SYNC_TOKEN_BO_OF_HSP = "sync_token_hsp_bo"
        const val SYNC_TOKEN_WEIGHT_OF_HSP = "sync_token_hsp_weight"
        const val SYNC_TOKEN_NUTRITION_OF_HSP = "sync_token_hsp_nutrition"
        const val SYNC_TOKEN_DISTANCE_OF_HSP = "sync_token_hsp_distance"
        const val SYNC_TOKEN_TOTAL_ENERGY_OF_HSP = "sync_token_total_energy"
    }


}