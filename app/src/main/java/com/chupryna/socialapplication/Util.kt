package com.chupryna.socialapplication

import android.content.Context
import android.net.ConnectivityManager

fun Context.isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networks = connectivityManager.allNetworks
    if (networks.isNotEmpty())
        return true

    return false
}