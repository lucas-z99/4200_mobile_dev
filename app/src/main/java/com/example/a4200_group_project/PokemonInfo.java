package com.example.a4200_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PokemonInfo extends AppCompatActivity {
    ImageView pokeImage;
    TextView pokeId;
    TextView pokeName;
    TextView pokeType1;
    TextView pokeType2;
    TextView pokeDescription;
    TextView pokeHeight;
    TextView pokeWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("PokemonInfo start");
        setContentView(R.layout.pokemon_info);
        System.out.println("PokemonInfo setContentView");
        // Fetch all views by id.
        pokeId = findViewById(R.id.pokeId);
        pokeImage = findViewById(R.id.pokeImage);
        pokeDescription = findViewById(R.id.pokeDescription);
        pokeName = findViewById(R.id.pokeName);
        pokeType1 = findViewById(R.id.pokeType1);
        pokeType2 = findViewById(R.id.pokeType2);
        pokeHeight = findViewById(R.id.pokeHeight);
        pokeWeight = findViewById(R.id.pokeWeight);

//        try {
//            setPokemonDataToView();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void setPokemonDataToView() throws IOException {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        pokeName.setText(sp.getString("name", null));
        pokeId.setText(sp.getString("id", null));
        pokeDescription.setText(sp.getString("description", null));
        pokeType1.setText(sp.getString("type1", null));
        pokeType2.setText(sp.getString("type2", null));
        pokeHeight.setText(sp.getString("height", null));
        pokeWeight.setText(sp.getString("weight", null));

        // https://stackoverflow.com/questions/18953632/how-to-set-image-from-url-for-imageview
        pokeImage.setImageBitmap(BitmapFactory.decodeStream(new URL(sp.getString("image_url", null)).openConnection().getInputStream()));
    }
}