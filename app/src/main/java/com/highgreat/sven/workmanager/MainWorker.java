package com.highgreat.sven.workmanager;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;

public class MainWorker extends Worker {

    //这个方法是在子线程执行的
    @NonNull
    @Override
    public Result doWork() {
        //上传，下载，同步数据。。。。。。
        Log.i("Sven","work执行");
        //获取mainActivity传入进来的数据
        String jettData=getInputData().getString("Sven");
        Log.i("Sven","work中取到了数据"+jettData);
        //把任务中的数据回传到activity中
        Data outputData = new Data.Builder().putString("name", "Sven").build();
        setOutputData(outputData);
        return Result.SUCCESS;
    }
}
