package com.example.myapplication

class MyUncaughtExceptionHandler : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        // 处理未捕获的异常

        println(throwable)
        throw throwable;
    }
}