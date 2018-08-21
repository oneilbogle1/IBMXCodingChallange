package com.example.oneilbogle.imbxcodechallenge;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView captureGps;

    Double Long;

    Double Lat;









    final static int REQUEST_LOCATION = 0;

    protected LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        captureGps = (TextView) findViewById(R.id.getgpstxt);

        Button getLocation =(Button) findViewById(R.id.getLocation);


        captureGps.setVisibility(View.GONE);






        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findLocation();

                }

         });



    }



    public void findLocation(){

        //check if permission has been granted
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(MainActivity.this,"Enable Location services",Toast.LENGTH_LONG).show();

            // Check Permissions Now
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
        // if permission has been granted
        else{

            //get location services
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {


//                String messageL = String.format(
//                        "Current Location \n Longitude: %1$s \n Latitude: %2$s",
//                        location.getLongitude(), location.getLatitude()
//                );



                Lat = location.getLatitude();

                Long = location.getLatitude();

                captureGps.setVisibility(View.VISIBLE);
                captureGps.setText("This is Long " + Long + " This is Lat " + Lat);


            }





        }
    }





}







