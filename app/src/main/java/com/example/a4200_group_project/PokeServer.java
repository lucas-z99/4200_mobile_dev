package com.example.a4200_group_project;

import androidx.core.util.Consumer;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PokeServer {

    // fetch data from server

    // a success call returns a JSON:
    // {"id": 1, "name": "Bulbasaur", "hp": 45, "attack": 49, "defense": 49, "spattack": 65, "spdefense": 65, "speed": 45, "type1": "Grass", "type2": "Poison"}

    // see PokeServer_Example


    //   public   ----------------------------------------------------------------

    public static void getPokemon(int id, Consumer<String> onSuccess, Consumer<Exception> onFail) {
        _server_query("http://18.216.183.227:8000/pokemon/" + id + "/", onSuccess, onFail);
    }

    public static void getPokemon(String name, Consumer<String> onSuccess, Consumer<Exception> onFail) {
        _server_query("http://18.216.183.227:8000/pokemon/name/" + name, onSuccess, onFail);
    }


    //   private   ----------------------------------------------------------------

    private static void _server_query(String _url, Consumer<String> onSuccess, Consumer<Exception> onFail) {

        Request request = new Request.Builder().url(_url).build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {

            // success
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();

                if (response.isSuccessful()) {
                    if (onSuccess != null) onSuccess.accept(content);
                } else {
                    if (onFail != null) onFail.accept(new Exception(content));
                }
            }

            // fail
            @Override
            public void onFailure(Call call, IOException e) {
                if (onFail != null) onFail.accept(e);
            }

        });

    }


}
