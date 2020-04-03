package com.highgreat.sven.workmanager;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;

public class MainWorker4 extends Worker {
    //这个方法是在子线程执行的
    @NonNull
    @Override
    public Result doWork() {
        //上传，下载，同步数据。。。。。。
        Log.i("Sven","work4执行了");
        return Result.SUCCESS;
    }
}







