package com.chupryna.socialapplication

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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

fun Context.shareImage(url: String) {
    //val path = Glide.getPhotoCacheDir(applicationContext, "").toString()

    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
        //putExtra(Intent.EXTRA_STREAM, Uri.parse(path))
        //type = "image/*"
    }
    startActivity(Intent.createChooser(intent, "Надіслати через"))
}