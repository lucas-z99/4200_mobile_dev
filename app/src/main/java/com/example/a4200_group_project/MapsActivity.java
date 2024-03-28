package com.example.a4200_group_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.a4200_group_project.databinding.ActivityMapsBinding;

import java.util.Random;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void getCurrentLocationAndGenerateMarkers() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    double baseLatitude = location.getLatitude();
                    double baseLongitude = location.getLongitude();
                    LatLng currentLocation = new LatLng(baseLatitude, baseLongitude);

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                    generateRandomMarkers(baseLatitude, baseLongitude, 10);
                }
            });
        }
    }

    private void generateRandomMarkers(double baseLatitude, double baseLongitude, int numberOfMarkers) {
        Random random = new Random();
        for (int i = 0; i < numberOfMarkers; i++) {
            // Generate a random displacement between -0.01 and 0.01 degrees for both latitude and longitude
            double latOffset = (random.nextDouble() - 0.5) / 50.0; // Adjust the divisor to spread or narrow the random points
            double lngOffset = (random.nextDouble() - 0.5) / 50.0;

            LatLng randomLatLng = new LatLng(baseLatitude + latOffset, baseLongitude + lngOffset);
            mMap.addMarker(new MarkerOptions().position(randomLatLng).title("Marker " + (i + 1)));
        }

        // Set the marker click listener
        mMap.setOnMarkerClickListener(marker -> {
            Toast.makeText(MapsActivity.this, marker.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
            // Performs the going to pokedex image page on click
            return false;
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        }

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // After map is ready, get the current location and generate markers
        getCurrentLocationAndGenerateMarkers();
    }
}
