package com.example.sampleproject.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.sampleproject.SampleApplication
import com.example.sampleproject.SampleApplication.Companion.appContext

object Util {
    fun isInternetConnected(): Boolean {
        val cm = SampleApplication.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getString(resourceId: Int, vararg value: Any?): String {
        return appContext().getString(resourceId, value)
    }
}