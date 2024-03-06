//package com.example.a4200_group_project;
//
//import android.app.Activity;
//
//import androidx.work.*;
//
//
//public class test_SQL extends Activity {
//
//
//    public void Test()
//    {
//        System.out.println("RunTest()");
//    }
//
//    void SQLWorkerTEST()
//    {
//        WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(test_SQLWorker.class).build();
//        android.content.Context c = this;
//        WorkManager mgr = WorkManager.getInstance(c);
//        mgr.enqueue(uploadWorkRequest);
//    }
//
//
//}
