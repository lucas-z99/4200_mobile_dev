package com.example.a4200_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    int pokemonId = 1;  // Keeps track of current pokemon
    String name;
    String type0;
    String type1;
    String description = "failed test";
    int height;
    int weight;
    String image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchAndSetPokemonData(pokemonId);


//        //TEST
//        PokeServer_Example p = new PokeServer_Example();
//        p.example();
    }

    public void fetchAndSetPokemonData(int id){
        PokeServer.getPokemon(id, this::onSuccess, this::onFail);
    }

    // For keeping track of pokemon data across pages.
    public void updateSharedPreferences(){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        spe.putInt("id", pokemonId);
        spe.putString("name", name);
        spe.putString("description", description);
        spe.putString("type0", type0);
        spe.putInt("height", height);
        spe.putInt("weight", weight);
        spe.putString("image_url", image_url);
        spe.apply();
    }

    // Update Pokemon date on successful request
    void onSuccess(String content) {
        System.out.println("  ----------   success  ----------\n");
        System.out.println(content);
        // Convert json string to json object.
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(content);
            pokemonId = (int) jsonObj.get("id");
            name = (String) jsonObj.get("name");
            type0 = (String) jsonObj.get("type0");
            type1 = (String) jsonObj.get("type1");
            description = (String) jsonObj.get("description");
            height = (int) jsonObj.get("height");
            weight = (int) jsonObj.get("weight");
            image_url = (String) jsonObj.get("image_url");
            updateSharedPreferences();
            updateViews();
        } catch (JSONException e) {
            System.out.println("Failed Jsonify json string.");
            throw new RuntimeException(e);
        }

    }

    // Update pokemon views
    public void updateViews(){

    }

    void onFail(Exception e) {
        System.out.println("  ----------   fail   ----------\n");
        e.printStackTrace();
    }

}