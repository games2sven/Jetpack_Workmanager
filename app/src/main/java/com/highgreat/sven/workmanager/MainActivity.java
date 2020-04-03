package com.highgreat.sven.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    //定义一个单次执行的任务
    OneTimeWorkRequest request;
    OneTimeWorkRequest request2;
    OneTimeWorkRequest request3;
    OneTimeWorkRequest request4;
    OneTimeWorkRequest request5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //后台工作约束
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .setRequiresDeviceIdle(true)//设备空闲
                .setRequiresStorageNotLow(true)
                .setRequiresCharging(true)//接通电源
                .build();

        //定义传入到任务中的数据
        Data inputData = new Data.Builder().putString("Sven", "Sven的数据").build();

        request = new OneTimeWorkRequest.Builder(MainWorker.class)
                .setInputData(inputData)
                .setConstraints(constraints)
                .build();
        //把任务加入到任务队列中，并在满足某种条件的情况去执行
//        WorkManager.getInstance().enqueue(request);

        //OneTimeorkRequest的初始化
        request2=new OneTimeWorkRequest.Builder(MainWorker2.class)
                .setInputData(inputData)
                .build();
        //把任务加入到任务队列中，并在满足某种条件的情况去执行
//        WorkManager.getInstance().enqueue(request2);

        //OneTimeorkRequest的初始化
        request3=new OneTimeWorkRequest.Builder(MainWorker3.class)
                .setInputData(inputData)
                .build();
        //把任务加入到任务队列中，并在满足某种条件的情况去执行
//        WorkManager.getInstance().enqueue(request3);

        //OneTimeorkRequest的初始化
        request4=new OneTimeWorkRequest.Builder(MainWorker4.class)
                .setInputData(inputData)
                .build();
        //把任务加入到任务队列中，并在满足某种条件的情况去执行
//        WorkManager.getInstance().enqueue(request4);

        //OneTimeorkRequest的初始化
        request5=new OneTimeWorkRequest.Builder(MainWorker5.class)
                .setInputData(inputData)
                .build();
        //把任务加入到任务队列中，并在满足某种条件的情况去执行
//        WorkManager.getInstance().enqueue(request5);

        //接收任务中回来的数据
        WorkManager.getInstance().getStatusById(request.getId())
                .observe(this, new Observer<WorkStatus>() {
                    @Override
                    public void onChanged(WorkStatus workStatus) {
                        if(workStatus != null && workStatus.getState().isFinished() ){
                            //在任务执行完成后
                            Log.i("Sven","activity取到了任务回传的数据"+workStatus.getOutputData().getString("name"));
                        }
                    }
                });

        //1,顺序执行
//        WorkManager.getInstance()
//                .beginWith(request3)
//                .then(request5)
//                .then(request)
//                .enqueue();
//        //2,分支执行
//        WorkManager.getInstance().beginWith(request3,request2).then(request4).enqueue();
//        //3,多任务链方式
//        WorkContinuation then1 = WorkManager.getInstance().beginWith(request).then(request2);
//        WorkContinuation then2 = WorkManager.getInstance().beginWith(request4).then(request5);
//        WorkContinuation.combine(then1,then2).then(request3).enqueue();

        //4,任务的唯一性
        //如果同一个任务被加入了队列两次以上，它会多次执行
        WorkManager.getInstance().beginUniqueWork("unique", ExistingWorkPolicy.REPLACE,request).enqueue();
    }
}
