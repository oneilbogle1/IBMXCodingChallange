package com.example.oneilbogle.imbxcodechallenge;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneilbogle.imbxcodechallenge.data.model.Get;
import com.example.oneilbogle.imbxcodechallenge.data.remote.APIService;
import com.example.oneilbogle.imbxcodechallenge.data.remote.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private APIService mAPIService;


    //used to find log message in Logcat
    String TAG = "GPS";


    TextView captureGps, postWea;

    Double Long;

    Double Lat;

    String PostGps;

    StringBuffer p = new StringBuffer();

    final static int REQUEST_LOCATION = 0;

    protected LocationManager locationManager;


    private WeatherAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        postWea = (TextView) findViewById(R.id.postWea);
        captureGps = (TextView) findViewById(R.id.getgpstxt);

        Button getLocation =(Button) findViewById(R.id.getLocation);


        captureGps.setVisibility(View.GONE);

        postWea.setVisibility(View.GONE);




        mAPIService = ApiUtils.getAPIService();
        mRecyclerView = (RecyclerView) findViewById(R.id.wea_answers);
        mAdapter = new WeatherAdapter(this, new ArrayList<Get>(0), new WeatherAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }

        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);



        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findLocation();
                LoadData(PostGps);

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


                    p.append(Lat);
                    p.append(",");
                    p.append(Long);

                    PostGps = p.toString();

                    captureGps.setVisibility(View.VISIBLE);
                    captureGps.setText("This is Long " + Long + " This is Lat " + Lat + " Send to back end " + PostGps);

                }

            }
        }



   private void LoadData(String postGps) {
//
//        mAPIService.getWeather(postGps).enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//
//                if(response.isSuccessful()) {
//                    showResponse(response.body().toString());
//                    Log.i(TAG, "post submitted to API." + response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//                Log.e(TAG, "Unable to submit post to API.");
//            }
//        });
//    }
//
//    public void showResponse(String response) {
//        if(postWea.getVisibility() == View.GONE) {
//            postWea.setVisibility(View.VISIBLE);
//        }
//        postWea.setText(response);


       mAPIService.getWeather().enqueue(new Callback<Get>() {
           @Override
           public void onResponse(Call<Get> call, Response<Get> response) {

               if(response.isSuccessful()) {
                   //mAdapter.updateAnswers(response.body().getWoeid());
                   Log.d("AnswersPresenter", "posts loaded from API");
               }else {
                   int statusCode  = response.code();
                   // handle request errors depending on status code
               }
           }

           @Override
           public void onFailure(Call<Get> call, Throwable t) {
               showErrorMessage();
               Log.d("AnswersPresenter", "error loading from API");

           }
       });
   }


    public void showErrorMessage() {
        Toast.makeText(this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }



}














