package com.example.myapplication

//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.webkit.WebView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.io.File
import java.io.OutputStream
import java.io.PrintWriter
import java.io.Writer


class MainActivity : ComponentActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {

        println("start...1")
        super.onCreate(savedInstanceState)

        //这里管理起来gui.xml文件。。
        setContentView(R.layout.activity_main)

        val webView1: WebView = findViewById(R.id.webView1)
        //是否与JavaScript交互
        webView1.settings.javaScriptEnabled = true
//是否打开Dom本地存储
        webView1.settings.domStorageEnabled = true
        webView1.settings.setAllowFileAccess(true)
        //   webView1.loadUrl("file:///android_asset/idx.htm")
        webView1.loadUrl("https://www.baidu.com")


        val TextView1: TextView = findViewById(R.id.txtOne)
        val text = TextView1.text.toString()
        TextView1.setText(TextView1.text.toString() +"aaaaaaaaaa")
        //主Actvitiy里面启动Service        1
        // startService(  Intent(this, ImgService.class))


        req_auth_foto();


//-------------------------------------------------- 设置查询的条件
        //这里需要的权限可能是acc all file也可以。。
        // 空数组
//        var projection = emptyArray<String>()
//         projection = { MediaStore.Images.Media.DATA };
        //projection=[_data]
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        var sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " DESC";

// 查询媒体数据库，获取所有图片的路径
        // INTERNAL_CONTENT_URI
     //   MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var cursor = getContentResolver().query(
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
                println("imagePath=》" + imagePath)
                TextView1.setText(TextView1.text.toString() +" newpath=>"+imagePath)

                // ...
            }
            println("finish...")
            // 关闭Cursor
            cursor.close();
        }



        ///req file prms

     //   ttt(this);
        req_file_prm(this, ::wrtFile );

    // this.context   context.getPackageName()
        // this.
       // this.packageName
        val uri = Uri.parse("package:"+getPackageName())  // this.getPackageName



      var prmsRzt=  ContextCompat.checkSelfPermission(     this,READ_EXTERNAL_STORAGE)
        var prmsRzt2=  ContextCompat.checkSelfPermission(     this, WRITE_EXTERNAL_STORAGE)
        var prmsRzt3=  ContextCompat.checkSelfPermission(     this, READ_MEDIA_IMAGES)


       //             wrtFile()
    }

    private fun req_auth_foto() {

        // 检查是否已经有了权限  PackageManager.PERMISSION_GRANTED =0
        val checkSelfPermission = ContextCompat.checkSelfPermission(  this,  android.Manifest.permission.READ_MEDIA_IMAGES        )
        if (checkSelfPermission    == PackageManager.PERMISSION_GRANTED) {
            println("alread have prms")
        }else
        {
            // checkSelfPermission==- 1
            println("not  have prms")
        }



        val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 101
        //   检查是否已经获取了读取相册权限
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            //android.Manifest.permission.READ_EXTERNAL_STORAGE
            //     ActivityCompat.requestPermissions  android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            var prmss = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            // 申请读取相册权限
            ActivityCompat.requestPermissions(
                this,
                prmss,
                READ_EXTERNAL_STORAGE_PERMISSION_CODE
            );

            prmss = arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES)
            ActivityCompat.requestPermissions(
                this,
                prmss,
                READ_EXTERNAL_STORAGE_PERMISSION_CODE
            );

            var  prmss2 = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this,  prmss2,1 );

            // 检查是否已经有了权限  PackageManager.PERMISSION_GRANTED =0
            val checkSelfPermission = ContextCompat.checkSelfPermission(  this,  android.Manifest.permission.WRITE_EXTERNAL_STORAGE        )
            if (checkSelfPermission    == PackageManager.PERMISSION_GRANTED) {
                println("alread have prms")
            }else
            {
                // checkSelfPermission==- 1
                println("not  have prms")
            }



        } else {
            // 已经获取了读取相册权限，可以进行后续操作
            // ...
        }

        var  prmss2 = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,  prmss2,1 );




        // Permission request logic
        val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            // Handle permission requests results
            // See the permission example in the Android platform samples: https://github.com/android/platform-samples
        }


        //not work
        var bldVerSdkInt=Build.VERSION.SDK_INT;  //34
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            //here
            requestPermissions.launch(arrayOf(Manifest.permission.WRITE_SETTINGS  ,Manifest.permission.WRITE_EXTERNAL_STORAGE,  WRITE_EXTERNAL_STORAGE,READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, READ_MEDIA_VISUAL_USER_SELECTED))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
        } else {
            requestPermissions.launch(arrayOf(READ_EXTERNAL_STORAGE))
        }


        // not effec for wrt file
        ActivityCompat.requestPermissions(this,  arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE,android.Manifest.permission.READ_MEDIA_IMAGES,READ_MEDIA_VISUAL_USER_SELECTED),1 );



    }


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//
//        }

        if (Environment.isExternalStorageManager()) {
            wrtFile();
        } else {
          //  ToastUtils.show("存储权限获取失败")
        }
    }

    private fun ttt(mainActivity: MainActivity) {

    }


}

fun rdFoto()
{

    val directory = File("/storage/emulated/0/DCIM/")
    if (directory.isDirectory) {
        val listFiles = directory.listFiles()
        var LSTFILEsIZE=     listFiles.size;  //==0
        listFiles.forEach {
            if (it.isFile) {
                println("File: ${it.name}")
             //   TextView1.setText(TextView1.text.toString() +" pth=>${it.name}")
            } else if (it.isDirectory) {
                println("Directory: ${it.name}")
             //   TextView1.setText(TextView1.text.toString() +" pth=>${it.name}")
            }
        }
    }
}

fun req_file_prm(thisForm:MainActivity, writeFileFun2025:()->Unit) {

//    startActivity(
//        Intent(
//            Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
//            uri
//        )
//    )

   // Build.VERSION.SDK_INT==34   Build.VERSION_CODES.R==30
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // 先判断有没有权限
        if (Environment.isExternalStorageManager()) {
            writeFileFun2025()
        } else {
            var action11=Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;
            val intent = Intent(action11)
            intent.setData(Uri.parse("package:" + thisForm.getPackageName()))
           // intent.setAction(action11)
            thisForm.startActivityForResult(intent, 111)
        }
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        // 先判断有没有权限
        if (ActivityCompat.checkSelfPermission(
                thisForm,
                READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                thisForm,
                WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            writeFileFun2025()
        } else {
            ActivityCompat.requestPermissions(
                thisForm,
                arrayOf<String>(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE),
                1
            )
        }
    } else {
        writeFileFun2025()
    }
}
fun  wrtFile()
{



    //  Environment.getExternalStorageDirectory==storage/emulate/0/
    val file = File(Environment.getExternalStorageDirectory().toString()+"/myfile.txt")
   // file.createNewFile()
  //  file.writeText("This will be written to the file!")
    file.appendText("good");
    println("ok")

    rdFoto()
//    val filename = "lg2025.txt"
//    val string = "Hello, World!呵呵呵哈哈哈"
//    val outputStream: FileOutputStream
//
//
//      outputStream = openFileOutput(filename, Context.MODE_PRIVATE)
//        outputStream.write(string.toByteArray())
//        outputStream.close()
}
fun writeFile() {
   val currentDir = System.getProperty("user.dir") + "\\out11"
    val file = File(  currentDir,"lg2024.txt")
    file.writeText("呵呵呵哈哈哈")
    println(file.readText())

//    file.writeBytes(byteArrayOf(12, 56, 83, 57))
//    println(file.readText())
//
//    //追加方式写入字节或字符
//    file.appendBytes(byteArrayOf(93, 85, 74, 93))
//    file.appendText("吼啊")
//    println(file.readText())

    //直接使用writer和outputstream
    val writer: Writer = file.writer()
    val outputStream: OutputStream = file.outputStream()
    val printWriter: PrintWriter = file.printWriter()
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