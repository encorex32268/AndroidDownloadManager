package com.lihan.androiddownloadmanager

interface Downloader {
    fun downloadFile(url : String) : Long
}