package com.example.a4200_group_project;

import android.app.Activity;

import androidx.work.*;


public class SQLTest extends Activity {

//    String host = "db-inst.c7wykyqioj0t.us-east-2.rds.amazonaws.com";
//    String dbname = "pokemonDB";
//    String username = "redandgreen";
//    String port = "5432";
//    String url = "";

//
//    //    String password = "M3PLHeydnxnFxTz9waelXECkVOp$-nZNZ3DP4l2cvGmvT!6eplf6u7KNcvWvoqQH";
//    String password = "33445566";

//    public String GetUrl() {
//
//        // url format:
//        // jdbc:postgresql://host:port/database
//        // (if host is ipv6 see docs)
//
//        return "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
//    }


    public void Test()
    {
        System.out.println("RunTest()");








    }

    void SQLWorkerTEST()
    {
        WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(SQLWorker.class).build();
        android.content.Context c = this;
        WorkManager mgr = WorkManager.getInstance(c);
        mgr.enqueue(uploadWorkRequest);
    }



//    public void web_request() {
//        try {
//            //
//            Connection conn = DriverManager.getConnection(url, username, password);
//            System.out.println("connect created");
//
//            //
//            Statement st = conn.createStatement();
//            System.out.println("connect success");
//
//            //
//            ResultSet result = st.executeQuery("SELECT * FROM ttt");
//            while (result.next()) {
//                System.out.print("Column 1 returned ");
//                System.out.println(result.getString(1));
//            }
//
////            ResultSetMetaData meta = result.getMetaData();
////            int col = meta.getColumnCount();
//
//
////
////            while (result.next()) {
////                for (int i = 0; i < col; i++) {
////                    if (i > 1)
////                        Log.d(tag, ",  ");
////
////
////                    String text = result.getString(i + 1);
////                    Log.d(tag, text + " " + meta.getCatalogName(i));
////                }
////            }
//
//            result.close();
//            st.close();
////
////            ResultSet resultSet = statement.executeQuery("SELECT id, name, age, desc, height FROM your_table");
////
////            while (resultSet.next()) {
////                int id = resultSet.getInt("id");
////                String name = resultSet.getString("name");
////                int age = resultSet.getInt("age");
////                String desc = resultSet.getString("desc");
////                float height = resultSet.getFloat("height");
////
////                // Log the fetched data
////                Log.d(TAG, "Row fetched: ID = " + id + ", Name = " + name + ", Age = " + age + ", Description = " + desc + ", Height = " + height);
////            }
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//
//
//    }

}
