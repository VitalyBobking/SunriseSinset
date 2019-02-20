package edu.sunrise.sunset.service;

import edu.sunrise.sunset.model.ApiSunriseAndSunset;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("json?lat=36.7201600&lng=-4.4203400")
    Call<ApiSunriseAndSunset> getApiSunriseAndSunset();
}
