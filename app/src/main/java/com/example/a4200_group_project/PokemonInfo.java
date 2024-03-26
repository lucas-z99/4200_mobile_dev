package com.example.a4200_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.squareup.picasso.Picasso;

public class PokemonInfo extends AppCompatActivity {
    ImageView pokeImage;
    TextView pokeId;
    TextView pokeName;
    TextView pokeType1;
    TextView pokeType2;
    TextView pokeDescription;
    TextView pokeHeight;
    TextView pokeWeight;

    ImageView backArrow;


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
        backArrow = findViewById(R.id.back_btn);
        try {
            setPokemonDataToView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainPage = new Intent(PokemonInfo.this, MainActivity.class);
                startActivity(mainPage);
            }
        });
    }


    public void setPokemonDataToView() throws IOException {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        pokeName.setText(extras.getString("name", null));
        pokeId.setText(String.valueOf(extras.getInt("id", 1)));
        pokeDescription.setText(extras.getString("description", null));
        pokeType1.setText(extras.getString("type1", null));
        pokeType2.setText(extras.getString("type2", null));
        pokeHeight.setText(extras.getString("height", null));
        pokeWeight.setText(extras.getString("weight", null));

        System.out.println("PokeInfo:" + extras.getString("description", null));
        // https://stackoverflow.com/questions/18953632/how-to-set-image-from-url-for-imageview
        Picasso.get()
                .load(extras.getString("image_url", null))
                .into(pokeImage);
    }
}