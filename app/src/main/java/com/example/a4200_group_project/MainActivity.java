package com.example.a4200_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    int pokemonId = 5;  // Keeps track of current pokemon, default 1
    String name;
    String type1;
    String type2;
    String description = "failed test";
    String height;
    String weight;
    String image_url;
    String jsonResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchAndSetPokemonData(pokemonId);


    }

    public void fetchAndSetPokemonData(int id){
        PokeServer.getPokemon(id, this::onSuccess, this::onFail);

    }

    // For keeping track of pokemon data across pages.
    public void goToPokeInfoPageWithData(){
        Intent pokeInfoPage = new Intent(this, PokemonInfo.class);
        pokeInfoPage.putExtra("name", name);
        pokeInfoPage.putExtra("id", pokemonId);
        pokeInfoPage.putExtra("description", description);
        pokeInfoPage.putExtra("type1", type1);
        pokeInfoPage.putExtra("type2", type2);
        pokeInfoPage.putExtra("height", height);
        pokeInfoPage.putExtra("weight", weight);
        pokeInfoPage.putExtra("image_url", image_url);
        startActivity(pokeInfoPage);
    }

    // Update Pokemon date on successful request
    void onSuccess(String content) {
        System.out.println("  ----------   success  ----------\n");
        System.out.println(content);
        jsonResponse = content;
        updatePokeData();
        updateViews();
    }

    // Update pokemon views
    public void updateViews(){
        System.out.println("updateViews");
        System.out.println(pokemonId);
        System.out.println(name);
        System.out.println(description);
        System.out.println(image_url);
    }

    public void updatePokeData() {
        JSONObject jsonObj = null;
        try {
            System.out.println("UpdatePokeData");
            jsonObj = new JSONObject(jsonResponse);
            pokemonId = (int) jsonObj.get("id");
            name = (String) jsonObj.get("name");
            type1 = (String) jsonObj.get("type0");
            type2 = (String) jsonObj.get("type1");
            description = (String) jsonObj.get("description");
            height = String.valueOf(jsonObj.get("height"));
            weight = String.valueOf(jsonObj.get("weight"));
            image_url = (String) jsonObj.get("image_url");
            System.out.println("UpdatePokeData Finished");
            goToPokeInfoPageWithData();
        } catch (JSONException e) {
            System.out.println("UpdatePokeData Failed");
            throw new RuntimeException(e);
        }

    }
    void onFail(Exception e) {
        System.out.println("  ----------   fail   ----------\n");
        e.printStackTrace();
    }

}