package com.lihan.androiddownloadmanager

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity(){


    private lateinit var progressBar : ProgressBar

    private val downloadCompletedReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "android.intent.action.DOWNLOAD_COMPLETE"){
                val id = intent.getLongExtra(
                    DownloadManager.EXTRA_DOWNLOAD_ID,-1L
                )
                if (id != -1L){
                    progressBar.visibility = View.GONE
                    Toast.makeText(context,"Download Finished", Toast.LENGTH_SHORT).show()
                    println("Finished")
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.GONE
        this.registerReceiver(downloadCompletedReceiver,
            IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"))
        progressBar.visibility = View.VISIBLE
        Thread.sleep(3000)
        val androidDownloader = AndroidDownloader(this@MainActivity)
        androidDownloader.downloadFile("https://plus.unsplash.com/premium_photo-1671129471248-82d1ba9dab31?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1614&q=80")


    }
}