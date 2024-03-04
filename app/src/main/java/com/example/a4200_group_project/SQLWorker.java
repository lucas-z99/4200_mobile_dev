package com.example.a4200_group_project;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.*;

import java.sql.*;

public class SQLWorker extends Worker {

    String host = "db-inst.c7wykyqioj0t.us-east-2.rds.amazonaws.com";
    String dbname = "pokemonDB";
    String port = "5432";
    String username = "redandgreen";
    //    String password = "M3PLHeydnxnFxTz9waelXECkVOp$-nZNZ3DP4l2cvGmvT!6eplf6u7KNcvWvoqQH";
    String password = "33445566";

    public SQLWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {

        DoSomething();

        return Result.success();
    }

    public void DoSomething() {
        System.out.println("DO SOMETHING!!!!!");


        try {
            Connection conn = DriverManager.getConnection(GetUrl(), username, password);
            System.out.println("connect created");

            Statement st = conn.createStatement();
            System.out.println("connect success");

            ResultSet result = st.executeQuery("SELECT * FROM ttt");
            while (result.next()) {
                System.out.print("Column 1 returned ");
                System.out.println(result.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String GetUrl() {

        // url format:
        // jdbc:postgresql://host:port/database
        // (if host is ipv6 see docs)

        return "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
    }

}
