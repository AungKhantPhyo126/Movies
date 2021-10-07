package com.aungkhantphyo.movies

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.aungkhantphyo.movies.workmanager.RefreshWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class Movies:Application() {
//    private val applicationScope = CoroutineScope(Dispatchers.Default)
//    override fun onCreate() {
//        super.onCreate()
//        refreshedDataInit()
//    }
//    private fun refreshedDataInit() {
//        applicationScope.launch {
//            setPeriodicWorkerRequest()
//        }
//    }
//
//    private fun setPeriodicWorkerRequest() {
//
//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresBatteryNotLow(true)
//            .setRequiresCharging(true)
//            .build()
//
//        val periodicWorkRequest = PeriodicWorkRequest
//            .Builder(RefreshWorker::class.java, 1, TimeUnit.DAYS)
//            .setConstraints(constraints)
//            .build()
//
//        WorkManager.getInstance(this).cancelAllWork()
//        WorkManager.getInstance(this).enqueue(periodicWorkRequest)
//
//    }
}