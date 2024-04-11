package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {

        println("start...")
        super.onCreate(savedInstanceState)

        //这里管理起来gui.xml文件。。
        setContentView(R.layout.activity_main)

        val webView1: WebView =findViewById(R.id.webView1)
        //是否与JavaScript交互
        webView1.settings.javaScriptEnabled = true
//是否打开Dom本地存储
        webView1.settings.domStorageEnabled = true
        webView1.settings.setAllowFileAccess(true)
        webView1.loadUrl("file:///android_asset/idx.htm")
      //  webView1.loadUrl("https://www.baidu.com")


        //主Actvitiy里面启动Service        1
       // startService(  Intent(this, ImgService.class))


        val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 101
      //   检查是否已经获取了读取相册权限
        if (ContextCompat.checkSelfPermission(this,  android.Manifest.permission.READ_EXTERNAL_STORAGE)
                              != PackageManager.PERMISSION_GRANTED)
        {
            val prmss = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            // 申请读取相册权限
            ActivityCompat.requestPermissions(this,
                prmss,
                READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        } else {
            // 已经获取了读取相册权限，可以进行后续操作
            // ...
        }


// 设置查询的条件
        // 空数组
//        var projection = emptyArray<String>()
//         projection = { MediaStore.Images.Media.DATA };

        val projection = arrayOf(MediaStore.Images.Media.DATA)
        var sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " DESC";

// 查询媒体数据库，获取所有图片的路径
        var   cursor = getContentResolver().query(
          MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
          projection,
          null,
          null,
          sortOrder
      );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // 获取图片路径
                var imagePath =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                // 对图片路径进行处理，如添加到列表中等
                println("imagePath=》"+imagePath)



                // ...
            }
            println("finish...")
            // 关闭Cursor
            cursor.close();
        }




    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name! 121313213",
            modifier = modifier
    )
}

// jeig haosyo dep
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android888")
    }
}