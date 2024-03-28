package com.example.a4200_group_project;

import static android.provider.MediaStore.Images.Media.getBitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int pokemonId = 1;  // Keeps track of current pokemon, default 1
    String name;
    String type1;
    String type2;
    String description = "failed test";
    String height;
    String weight;
    String image_url;
    String jsonResponse;
    ImageView poke_image;
    TextView poke_name;
    ImageView get_info_btn;
    ImageView left_btn;
    ImageView right_btn;

    ImageView  mapButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        pokemonId = intent.getIntExtra("pokemonId", 1);
        setContentView(R.layout.pokedex_intro);
        poke_image = findViewById(R.id.pokeImage);
        poke_name = findViewById(R.id.pokeName);
        right_btn = findViewById(R.id.right_btn);
        left_btn = findViewById(R.id.left_btn);
        get_info_btn = findViewById(R.id.get_info_btn1);
        mapButton = findViewById(R.id.map_button);
        fetchAndSetPokemonData(pokemonId);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        setViews();

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pokemonId < 151){
                    pokemonId += 1;
                    fetchAndSetPokemonData(pokemonId);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    setViews();
                }
            }
        });

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pokemonId > 1){
                    pokemonId -= 1;
                    fetchAndSetPokemonData(pokemonId);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    setViews();
                }
            }
        });

        get_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPokeInfoPageWithData();
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    public void fetchAndSetPokemonData(int id){
        PokeServer.getPokemon(id, this::onSuccess, this::onFail);
    }

    public void setViews(){
        poke_name.setText(name);
        Picasso.get()
                .load(image_url)
                .into(poke_image);
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