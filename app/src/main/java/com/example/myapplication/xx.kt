//package com.example.myapplication
//
//import android.provider.MediaStore
//
//// 设置查询的条件
//String[] projection = {MediaStore.Images.Media.DATA};
//String sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " DESC";
//
//// 查询媒体数据库，获取所有图片的路径
//Cursor cursor = getContentResolver().query(
//MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//projection,
//null,
//null,
//sortOrder
//);
//
//if (cursor != null) {
//    while (cursor.moveToNext()) {
//        // 获取图片路径
//        String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//
//        // 对图片路径进行处理，如添加到列表中等
//        // ...
//    }
//
//    // 关闭Cursor
//    cursor.close();
//}
//
