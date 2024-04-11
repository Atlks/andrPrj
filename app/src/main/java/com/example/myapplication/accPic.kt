//package com.example.myapplication
//
//query?.use { cursor ->
//    // Cache column indices.
//    val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
//    val nameColumn =
//        cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
//    val durationColumn =
//        cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
//    val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
//
//    while (cursor.moveToNext()) {
//        // Get values of columns for a given video.
//        val id = cursor.getLong(idColumn)
//        val name = cursor.getString(nameColumn)
//        val duration = cursor.getInt(durationColumn)
//        val size = cursor.getInt(sizeColumn)
//
//        val contentUri: Uri = ContentUris.withAppendedId(
//            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
//            id
//        )
//
//        // Stores column values and the contentUri in a local object
//        // that represents the media file.
//        videoList += Video(contentUri, name, duration, size)
//    }
//}