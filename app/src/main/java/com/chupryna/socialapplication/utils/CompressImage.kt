package com.chupryna.socialapplication.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class CompressImage(context: Context) {

    private val tempFile = File(context.cacheDir, String.format("photo_profile_%s.jpg", System.currentTimeMillis()))

    fun compressBitmap(file: File, sampleSize: Int, quality: Int): File {
        if (file.length() / 1024 < SIZE_LIMIT) {
            return file
        }

        try {
            val inputStream = FileInputStream(file)
            val options = BitmapFactory.Options()
            options.inSampleSize = sampleSize
            val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, options)
            inputStream.close()

            val outputStream = FileOutputStream(tempFile)
            selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.close()

            val lengthInKb = tempFile.length() / 1024
            if (lengthInKb > SIZE_LIMIT)
                compressBitmap(tempFile, sampleSize, quality)

            selectedBitmap.recycle()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tempFile
    }

    companion object {
        private const val SIZE_LIMIT = 300  //300 Кb
    }
}