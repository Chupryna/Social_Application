package com.chupryna.socialapplication.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chupryna.socialapplication.R

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
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    startActivity(Intent.createChooser(intent, "Надіслати через"))
}
    /*val task = Runnable {
        val file: Bitmap = Glide.with(this).asBitmap().load(url).submit().get()
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, file.compress(Bitmap.CompressFormat.JPEG, ))
            type = "image/*"
        }
        startActivity(Intent.createChooser(intent, "Надіслати через"))
    }
    val t = Thread(task)
    t.start()*/