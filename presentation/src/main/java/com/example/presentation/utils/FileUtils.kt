package com.example.presentation.utils

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Throws(IOException::class)
fun createTemporaryFile(context: Context): File {
    val imageName = generateImageName()
    val storageDir =
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(imageName, ".jpg", storageDir)
}

private fun generateImageName(): String {
    val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH)
    val timeStamp = simpleDateFormat.format(Date())
    return "JPEG" + timeStamp + "_"
}