package com.assignment.common.logger

import android.util.Log
import com.assignment.common.BuildConfig
import javax.inject.Inject

private const val TAG = "CleanRestro==>"

/**
 * Concrete implementation of [Logger].
 */
class LoggerImpl @Inject constructor() : Logger {


    override fun debug(message: String) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, message)
    }

    override fun error(message: String) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, message)
    }

}