package com.lihan.androiddownloadmanager

import android.app.DownloadManager
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.core.net.toUri

@RequiresApi(Build.VERSION_CODES.M)
class AndroidDownloader(
    private val context: Context
) : Downloader{

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"jau${System.currentTimeMillis()}.jpeg")
        return downloadManager.enqueue(request)

    }
}