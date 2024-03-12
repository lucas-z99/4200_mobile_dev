//package com.example.a4200_group_project;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//import androidx.work.*;
//
//import java.sql.*;
//
//public class test_SQLWorker extends Worker {
//
//    String host = "";
//    String dbname = "";
//    String port = "";
//    String username = "";
//    String password = "";
//
//    public test_SQLWorker(@NonNull Context context, @NonNull WorkerParameters params) {
//        super(context, params);
//    }
//
//    @NonNull
//    @Override
//    public Result doWork() {
//
//        DoSomething();
//
//        return Result.success();
//    }
//
//    public void DoSomething() {
//        System.out.println("DO SOMETHING!!!!!");
//
//
//        try {
//            Connection conn = DriverManager.getConnection(GetUrl(), username, password);
//            System.out.println("connect created");
//
//            Statement st = conn.createStatement();
//            System.out.println("connect success");
//
//            ResultSet result = st.executeQuery("SELECT * FROM ttt");
//            while (result.next()) {
//                System.out.print("Column 1 returned ");
//                System.out.println(result.getString(1));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    public String GetUrl() {
//
//        // url format:
//        // jdbc:postgresql://host:port/database
//        // (if host is ipv6 see docs)
//
//        return "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
//    }
//
//}
