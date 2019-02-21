package edu.sunrise.sunset.service;

import edu.sunrise.sunset.model.ApiSunriseAndSunset;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("json?lat=52.18007&lng=20.518369")
    Call<ApiSunriseAndSunset> getApiSunriseAndSunset(@Query("lat") double lat, @Query("lng") double lng);
}
