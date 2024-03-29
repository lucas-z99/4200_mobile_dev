package com.example.a4200_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    ImageView mapButton;

    static boolean flag_cache;
    List<Integer> cached_id = new ArrayList<>();

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


        ChoosePokemon(pokemonId);


        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokemonId < 151) {
                    ChoosePokemon(++pokemonId);
                }
            }
        });

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokemonId > 1) {
                    ChoosePokemon(--pokemonId);
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

        //
        if (!flag_cache) {
            flag_cache = true;

            for (int i = 1; i < 30; i++) {
                PokeServer.getPokemon(i, this::fun, this::onFail);

//                if (i % 30 == 0)
//                    try {
//                        TimeUnit.SECONDS.sleep(3);
//                    } catch (RuntimeException | InterruptedException e) {
//                        //
//                    }
            }
        }
    }


    public void fun(String content) {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(content);
            int id = (int) jsonObj.get("id");
            String url = (String) jsonObj.get("image_url");

            if (cached_id.contains(id))
                return;

            cached_id.add(id);
            System.out.println("AWS server return: " + id); // debug

            Picasso.get()
                    .load(url)
                    .fetch(new Callback() {
                        @Override
                        public void onSuccess() {
                            System.out.println("Piccaso cache complete");
                            //do nothing
                        }

                        @Override
                        public void onError(Exception e) {
                            //do nothing
                        }
                    });


        } catch (JSONException e) {
//
        }

    }

    public void ChoosePokemon(int id) {
        PokeServer.getPokemon(id, this::UpdateUI, this::onFail);
    }

    public void setViews() {
        poke_name.setText(name);
        Picasso.get()
                .load(image_url)
                .into(poke_image);
    }

    // For keeping track of pokemon data across pages.
    public void goToPokeInfoPageWithData() {
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
    void UpdateUI(String content) {
        System.out.println("  ----------   success  ----------\n");
        System.out.println(content);

        jsonResponse = content;
        updatePokeData();

        runOnUiThread(this::setViews); // so we don't block main thread
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