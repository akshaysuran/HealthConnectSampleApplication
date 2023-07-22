package com.example.myapplication


import androidx.health.connect.client.HealthConnectClient

object HspDataClient {

    fun getClient(): HealthConnectClient? {
        //todo need to replace with isSdkSupported
        return if(HealthConnectClient.getSdkStatus(ContextHolder.getContext()) == HealthConnectClient.SDK_AVAILABLE) {
            try {
                HealthConnectClient.getOrCreate(ContextHolder.getContext())
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }
}