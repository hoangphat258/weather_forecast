package com.example.weatherforecast.utils

import android.os.Build
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


object DeviceUtils {

    fun isDeviceRooted(): Boolean {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3() || checkRootMethod4()
    }

    private fun checkRootMethod1(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootMethod2(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )
        for (path in paths) {
            if (File(path).exists()) return true
        }
        return false
    }

    private fun checkRootMethod3(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            val `in` = BufferedReader(InputStreamReader(process.inputStream))
            `in`.readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }

    private fun checkRootMethod4(): Boolean {
        var found = false
        if (!found) {
            val places = arrayOf(
                "/sbin/", "/system/bin/", "/system/xbin/",
                "/data/local/xbin/", "/data/local/bin/",
                "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"
            )
            for (where in places) {
                if (File(where + "su").exists()) {
                    found = true
                    break
                }
            }
        }
        return found
    }

//    fun hasRootAccess(): Boolean {
//        try {
//            val s = Scanner(
//                Runtime.getRuntime().exec(arrayOf("/system/bin/su", "-c", "cd / && ls")).inputStream
//            ).useDelimiter("\\A")
//            return (if (s.hasNext()) s.next() else "") != ""
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        return false
//    }

}