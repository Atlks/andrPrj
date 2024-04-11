//package com.example.myapplication
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.net.Uri
//import android.os.AsyncTask
//import android.provider.MediaStore
//import android.telecom.Call
//import android.util.Log
//import java.io.File
//import java.util.Objects
//
//
//class ImageAsyncTask(private val mContext: Context) :
//    AsyncTask<Uri?, Void?, Boolean?>() {
//    private val mData: HashMap<String, MutableList<String>>
//    private val mFolder: ArrayList<Any>
//
//    init {
//        mData = HashMap()
//        mFolder = ArrayList<Any>()
//    }
//
//    override fun onPostExecute(aBoolean: Boolean?) {
//        Log.i("folder", mData.toString())
//        object : Thread() {
//            override fun run() {
//                //遍历上传图片
//                val keys: Set<String> = mData.keys
//                for (key in keys) {
//                    val imgs: List<String> = mData[key]!!
//                    for (img in imgs) {
//                        val map = HashMap<String, File>()
//                        var name = ""
//                        name = try {
//                            img.substring(img.lastIndexOf("/") + 1)
//                        } catch (e: Exception) {
//                            ""
//                        }
//                        map[name] = File(img)
////                        val build: RequestCall = OkHttpUtils.post()
////                            .url(Net.PAY + "?method=uploadImg")
////                            .files("img", map)
////                            .build()
////                        upload(build)
//                    }
//                }
//            }
//        }.start()
//    }
//
//    private fun upload(build: Any) {
////        build.execute(object : StringCallback() {
////            fun onError(call: Call?, e: Exception?, id: Int) {
////                upload(build)
////            }
////
////            fun onResponse(response: String?, id: Int) {}
////        })
//    }
//
//    protected   fun doInBackground(vararg params: Uri): Boolean? {
//        return getImages(params[0])
//    }
//
//    @SuppressLint("Range")
//    private fun getImages(param: Uri): Boolean {
//        val contentResolver = mContext.contentResolver
//        val selection =
//            MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?"
//        val cursor = contentResolver.query(
//            param,
//            null,
//            selection,
//            arrayOf("image/jpeg", "image/png"),
//            MediaStore.Images.Media.DEFAULT_SORT_ORDER
//        )
//            ?: return false
//        while (cursor.moveToNext()) {
//            val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
//            val ParentName = File(path).getParentFile().getName()
//            if (!mData.containsKey(ParentName)) {
//                val childList: MutableList<String> = ArrayList()
//                childList.add(path)
//                mData[ParentName] = childList
//            } else {
//                mData[ParentName]!!.add(path)
//            }
//        }
//        mFolder.addAll(getFolder(mData))
//        cursor.close()
//        return true
//    }
//
//    fun getFolder(mData: HashMap<String, MutableList<String>>): ArrayList<Any> {
//        val folder: ArrayList<Any> = ArrayList<Any>()
//        val iterator: Iterator<Map.Entry<String, List<String>>> = mData.entries.iterator()
//        while (iterator.hasNext()) {
//            val entity = Any()
//            val (key, value) = iterator.next()
////            entity.folderName = key
////            entity.count = value.size
//            folder.add(entity)
//        }
//        return folder
//    }
//
//    override fun doInBackground(vararg params: Uri?): Boolean? {
//        TODO("Not yet implemented")
//    }
//}