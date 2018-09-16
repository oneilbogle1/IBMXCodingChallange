package com.example.oneilbogle.imbxcodechallenge.data.remote;

import com.example.oneilbogle.imbxcodechallenge.data.model.Get;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    //  api/location/search/?lattlong=36.96,-122.02

    @GET("/api/location/search/?lattlong=")
    Call<Get> getWeather();



    @GET("/api/location/search/?lattlong=")
    Call<Get> getWeather(@Query("location") String loc);



}
