package com.example.myapplication

import android.util.Log
import androidx.health.connect.client.aggregate.AggregationResult
import androidx.health.connect.client.records.Record
import androidx.health.connect.client.records.SleepStageRecord
import androidx.health.connect.client.request.AggregateRequest
import androidx.health.connect.client.request.ChangesTokenRequest
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.response.ChangesResponse
import androidx.health.connect.client.response.InsertRecordsResponse
import androidx.health.connect.client.response.ReadRecordResponse
import androidx.health.connect.client.response.ReadRecordsResponse
import androidx.health.connect.client.time.TimeRangeFilter
import java.time.Instant
import kotlin.reflect.KClass

object HspHealthDataHelper {
    private const val TAG = "HSPTEST-HspHealthDataHelper"
    private const val SECOND = 1000L
    private const val MINUTE = 60 * SECOND
    private const val HOUR = 60 * MINUTE
    private const val DAY = 24 * HOUR
    private const val MONTH = 30 * DAY

    suspend fun insertHealthRecords(records: List<Record>): InsertRecordsResponse {
        Log.i(TAG, "insertHealthRecords - dataType")
        return HspDataClient.getClient()?.insertRecords(records)
            ?: throw NullPointerException("Client is Null")
    }

    suspend fun updateHealthRecords(records: List<Record>, dataType: String) {
        Log.i(TAG, "updateHealthRecords - dataType: $dataType")
        HspDataClient.getClient()?.updateRecords(records)
    }

    suspend fun deleteHealthRecords(recordType: KClass<out Record>, recordIdsList: List<String>) {
        Log.i(TAG, "deleteHealthRecords recordType $recordType")
        HspDataClient.getClient()?.deleteRecords(recordType, recordIdsList, listOf())
            ?: throw NullPointerException("Client is Null")
    }

    suspend fun deleteHealthRecords(
        recordType: KClass<out Record>,
        timeRangeFilter: TimeRangeFilter
    ) {
        Log.i(TAG, "deleteHealthRecords")
        HspDataClient.getClient()?.deleteRecords(recordType, timeRangeFilter)
    }

    suspend fun <T : Record> readHealthRecords(request: ReadRecordsRequest<T>): ReadRecordsResponse<T> {
        Log.i(TAG, "readHealthRecords")
        return HspDataClient.getClient()?.readRecords(request)
            ?: throw NullPointerException("Client is Null")
    }

    suspend fun <T : Record> readHealthRecord(
        recordType: KClass<T>,
        recordId: String
    ): ReadRecordResponse<T> {
        Log.i(TAG, "readHealthRecord")
        return HspDataClient.getClient()?.readRecord(recordType, recordId)
            ?: throw NullPointerException("Client is Null")
    }

    suspend fun readChangeData(changesToken: String): ChangesResponse {
        return HspDataClient.getClient()?.getChanges(changesToken)
            ?: throw NullPointerException("Client is Null")
    }

    suspend fun getChangesToken(request: ChangesTokenRequest): String {
        return HspDataClient.getClient()?.getChangesToken(request)
            ?: throw NullPointerException("Client is Null")
    }

    suspend fun readAggregatedData(request: AggregateRequest): AggregationResult? =
        HspDataClient.getClient()?.aggregate(request)

    private fun getTimeSpec1(startTime: Long, endTime: Long): TimeRangeFilter {
        return TimeRangeFilter.Companion.between(
            Instant.ofEpochMilli(startTime),
            Instant.ofEpochMilli(endTime)
        )
    }

    suspend fun readSleepStageRecords(): ReadRecordsResponse<SleepStageRecord> {
        val endTime = System.currentTimeMillis()
        val startTime = endTime - HOUR
        val timeFilter =
            TimeRangeFilter.between(Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(endTime))
        return readHealthRecords(ReadRecordsRequest(SleepStageRecord::class, timeFilter))
    }
}