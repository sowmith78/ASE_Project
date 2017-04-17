package com.example.application.locus_proj;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Double lat;
    Double lan;
    //Bundle extras;
    String source;
    List<Address> lst= new ArrayList<>();
    Double latitude;
    Double longitude;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle extras = getIntent().getExtras();
        Toast.makeText(MapsActivity.this," ",Toast.LENGTH_SHORT).show();
        if (extras != null) {
            source=extras.getString("class");
            lat = extras.getDouble("latitude");
            lan= extras.getDouble("longitude");
            message=extras.getString("Place");
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(source.equals("usertracker")) {
            Toast.makeText(MapsActivity.this, "In Maps onMap Ready functiom", Toast.LENGTH_SHORT);
            LatLng sydney = new LatLng(lat, lan);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
        else{
            try {
                Geocoder geocoder= new Geocoder(this);
                lst= geocoder.getFromLocationName(message,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(Address address:lst){
                latitude=address.getLatitude();
                longitude=address.getLongitude();
                LatLng sydney = new LatLng(latitude, longitude);
                Toast.makeText(MapsActivity.this,"Value showed in map"+latitude+" "+longitude, Toast.LENGTH_SHORT).show();
                mMap.addMarker(new MarkerOptions().position(sydney).title("address"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
        }
    }
}
