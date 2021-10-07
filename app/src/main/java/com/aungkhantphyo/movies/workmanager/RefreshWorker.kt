package com.aungkhantphyo.movies.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aungkhantphyo.movies.repository.MovieRepo

class RefreshWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val movieRepo: MovieRepo
) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        return try {
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }

    }
}