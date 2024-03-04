package com.example.a4200_group_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;

import android.os.Bundle;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView textView = findViewById(R.id.textView);


//        ServerTest();
//        SQLTest_WholeTable();

        // query test
        SearchPokemon(3, this::you_got_mail, null);
        SearchPokemon("Charizard", this::you_got_mail, null);

    }

    void you_got_mail(String response) {
        System.out.println(response);
    }


    //   public   ----------------------------------------------------------------

    public void SearchPokemon(int id, Consumer<String> onSuccess, Consumer<IOException> onFailure) {
        _server_query("http://18.216.183.227:8000/pokemon/" + id, onSuccess, onFailure);
    }


    public void SearchPokemon(String name, Consumer<String> onSuccess, Consumer<IOException> onFailure) {
        _server_query("http://18.216.183.227:8000/pokemon/name/" + name, onSuccess, onFailure);
    }


    //   private   ----------------------------------------------------------------

    private void _server_query(String _url, Consumer<String> onSuccess, Consumer<IOException> onFailure) {

        // request SQL data from server
        // will call back on success / fail

        Request request = new Request.Builder().url(_url).build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (onFailure != null) onFailure.accept(e); // fail
                else e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String re = response.body().string();
                    if (onSuccess != null) onSuccess.accept(re); // success
                    else System.out.println("response = " + re);
                }
            }
        });
    }


    //   test   ----------------------------------------------------------------

    void ServerTest() {
        String url = "http://18.216.183.227:8000/test";

        Request request = new Request.Builder().url(url).build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override

            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String re = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            System.out.println("connect success");
                            System.out.println(re);
                        }
                    });
                }
            }
        });
    }


    void SQLTest_WholeTable() {
        String url = "http://18.216.183.227:8000/pokemon/";

        Request request = new Request.Builder().url(url).build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String re = response.body().string();
                    System.out.println(re);
                }
            }
        });
    }


}