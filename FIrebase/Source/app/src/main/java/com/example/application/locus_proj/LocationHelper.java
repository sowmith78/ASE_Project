package com.example.application.locus_proj;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by satheeshchandra on 3/27/17.
 */
//suppress warnings

public class LocationHelper extends Service{

    private LocationListener listener;
    private LocationManager locationManager;

    public IBinder onBind(Intent intent){
        return null;

    }
    public  void onCreate(){
        Toast.makeText(LocationHelper.this,"service started",Toast.LENGTH_SHORT).show();
            listener= new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Toast.makeText(LocationHelper.this,"on locationchanged()"+ +location.getLongitude()+" "+location.getLatitude(),Toast.LENGTH_SHORT).show();
                    Intent i= new Intent("location_update");
                    i.putExtra("coordinates",location.getLongitude()+" "+location.getLatitude());
                    sendBroadcast(i);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {
                    Intent i=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            };
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0, listener);
    }
    public void onDestroy(){
        super.onDestroy();
        if(locationManager!=null){
                locationManager.removeUpdates(listener);
        }
    }
}
