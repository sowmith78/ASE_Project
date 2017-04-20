package com.example.application.locus_proj;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavoriteLocationManager extends AppCompatActivity {
    private static final int PLACE_PICKER_REQUEST = 1;
    final ArrayList<String> list = new ArrayList<String>();
    String[] values = new String[] {};
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public String user;
    String muid;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_location_manager);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        muid=user.replace(".","@");
        Toast.makeText(FavoriteLocationManager.this,"in Fav activty "+user, Toast.LENGTH_SHORT ).show();
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivityForResult(builder.build(this),PLACE_PICKER_REQUEST);
            }
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }//oncreate()
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK)
            {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("%s is successfully added to your favourite location", place.getName());
                final ListView listview = (ListView) findViewById(R.id.listview);
                for (int i = 0; i < values.length; ++i)
                {
                    list.add(values[i]);
                }
                list.add(place.getName()+": "+String.valueOf(place.getAddress()));
                myRef.child("users").child(muid).child("favoritelocation").push().setValue(list);

                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                final StableArrayAdapter adapter = new StableArrayAdapter(this,
                        android.R.layout.simple_list_item_1, list);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        Intent i= new Intent(FavoriteLocationManager.this,MapsActivity.class);
                        i.putExtra("Place",item);
                        i.putExtra("class","Favlocation");
                        startActivity(i);
                    }

                });
            }
            else {
                  Toast.makeText(FavoriteLocationManager.this,"I am worried", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void addMoreLocations(View view)
    {
        Intent i= new Intent(FavoriteLocationManager.this, FavoriteLocationManager.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


class StableArrayAdapter extends ArrayAdapter<String>
{
    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }
    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}


