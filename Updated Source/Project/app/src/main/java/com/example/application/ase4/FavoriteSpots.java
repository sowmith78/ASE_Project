package com.example.application.ase4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class FavoriteSpots extends AppCompatActivity {

/*
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);

    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("Place: " , place.getName().toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i("msg", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
                }
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_spots);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            @Override
            public void onPlaceSelected(Place place) {
                //TODO: GET info about the selected place.
                Log.i("msg",place.getName().toString());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("msg", "An error occurred: " + status);
            }
        });
    }


}
