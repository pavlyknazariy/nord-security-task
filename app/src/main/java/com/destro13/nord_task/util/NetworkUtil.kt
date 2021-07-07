package com.destro13.nord_task.util

import java.net.InetAddress


object NetworkUtil {
    fun isInternetAvailable(): Boolean {
        return try {
            val ipAddr: InetAddress = InetAddress.getByName("www.google.com")
            !ipAddr.equals("")
        } catch (e: Exception) {
            false
        }
    }
}

