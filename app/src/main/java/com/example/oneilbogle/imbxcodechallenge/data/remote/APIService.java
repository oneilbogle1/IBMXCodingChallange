package com.example.oneilbogle.imbxcodechallenge.data.remote;

import com.example.oneilbogle.imbxcodechallenge.data.model.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/api/location/search/?lattlong=")
    @FormUrlEncoded
    Call<Post> savePost(@Field("distance") Integer distance,
                        @Field("title") String title,
                        @Field("location_type") String locationType,
                        @Field("woeid") Integer woeid,
                        @Field("latt_long") String lattLong);



}
