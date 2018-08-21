package com.example.oneilbogle.imbxcodechallenge.data.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://www.metaweather.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }


}
