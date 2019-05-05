package com.chupryna.socialapplication

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun Context.isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networks = connectivityManager.allNetworks
    if (networks.isNotEmpty())
        return true

    return false
}

fun ImageView.loadPhoto(path: String?) {
    if (path == null)
        return
    Glide.with(context)
        .load(path)
        .placeholder(R.drawable.ic_launcher_background)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}