package edu.sunrise.sunset.network;



import edu.sunrise.sunset.model.ApiSunriseAndSunset;
import edu.sunrise.sunset.service.RetrofitService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URL;
import java.net.URLConnection;

public class RetrofitRequestController implements Callback<ApiSunriseAndSunset> {
    private static final String BASE_URL = "https://api.sunrise-sunset.org/";
    private static final Logger log = Logger.getLogger(RetrofitRequestController.class);
    private RetrofitService service;

    public RetrofitRequestController() {
        create();
    }

    private void create() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(RetrofitService.class);
        }

    public void onResponse(Call<ApiSunriseAndSunset> call, Response<ApiSunriseAndSunset> response) {

        ApiSunriseAndSunset list = response.body();

        try{
            list.getStatus().equalsIgnoreCase("ok");
            System.out.println("Sunrise :" + list.getResults().getSunrise());
            System.out.println("Sunset :" + list.getResults().getSunset());
            System.out.println("Sunrise :" + list.getResults().getSunrise()+ " / "
                                + "Sunset : " +list.getResults().getSunset());
            log.info("Status : " + response.body().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Status ERROR" + list.getStatus());
        }
    }

    public void onFailure(Call<ApiSunriseAndSunset> call, Throwable throwable) {
        log.error("onFailure : " + throwable.getMessage());
    }

    public void requestData(double[] latLngByIdCity) {
        if (internetIsAvailable()) {
            Call<ApiSunriseAndSunset> call = service.getApiSunriseAndSunset(latLngByIdCity[0], latLngByIdCity[1]);
            call.enqueue(this);
        } else {
            System.out.println("check internet connection");
            log.error("check internet connection");
        }
    }

    private boolean internetIsAvailable() {
        try {
            URL url = new URL(BASE_URL);
            URLConnection connection = url.openConnection();
            connection.connect();
            log.info("Connection Successful");

        }
        catch (Exception e) {
            log.error("Internet Not Connected : " + e.getMessage());
            return false;
        }
        return true;
    }
}
