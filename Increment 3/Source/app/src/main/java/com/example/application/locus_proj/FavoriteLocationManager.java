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
    private String id;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_location_manager);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
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
                for (int i = 0; i < values.length; ++i) {
                    list.add(values[i]);
                }
                list.add(place.getName()+": "+String.valueOf(place.getAddress()));
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                //id=myRef.push().getKey();
                //Locationlist lt=new Locationlist(id,list);
                //myRef.child(id).setValue(lt);
                //displaying locations
                final StableArrayAdapter adapter = new StableArrayAdapter(this,
                        android.R.layout.simple_list_item_1, list);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            view.animate().setDuration(2000).alpha(0)
                                    .withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                            list.remove(item);
                                            //Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                                            //EditText editText = (EditText) findViewById(R.id.editText);
                                            // String message = editText.getText().toString();
                                            //intent.putExtra(EXTRA_MESSAGE,list.remove(item));
                                            //startActivity(intent);
                                            adapter.notifyDataSetChanged();
                                            view.setAlpha(1);
                                        }
                                    });
                        }
                    }

                });
            }
        }
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


