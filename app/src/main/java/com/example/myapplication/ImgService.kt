package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.provider.MediaStore
import androidx.annotation.Nullable


class ImgService : Service() {
    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {}
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        //启动一个异步任务来读取本地相册所有图片
    //    ImageAsyncTask(this).execute(uri)
        return super.onStartCommand(intent, START_FLAG_REDELIVERY, startId)
    }
}