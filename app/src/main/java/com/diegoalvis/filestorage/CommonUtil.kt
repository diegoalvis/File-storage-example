package com.diegoalvis.filestorage

import android.os.Environment
import android.util.Log


object CommonUtil {
    fun isSdReadable(): Boolean {
        var mExternalStorageAvailable = false
        try {
            val state = Environment.getExternalStorageState()
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // We can read and write the media
                mExternalStorageAvailable = true
                Log.i("isSdReadable", "External storage card is readable.")
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                // We can only read the media
                Log.i("isSdReadable", "External storage card is readable.")
                mExternalStorageAvailable = true
            } else {
                // Something else is wrong. It may be
                // one of many other states, but all
                // we need to know is we can neither
                // read nor write
                mExternalStorageAvailable = false
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return mExternalStorageAvailable
    }
}
