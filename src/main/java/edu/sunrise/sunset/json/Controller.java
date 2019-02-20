package edu.sunrise.sunset.json;



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

public class Controller implements Callback<ApiSunriseAndSunset> {
    private static final String BASE_URL = "https://api.sunrise-sunset.org/";
    private static final Logger log = Logger.getLogger(Controller.class);
    public void start() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitService service = retrofit.create(RetrofitService.class);

            Call<ApiSunriseAndSunset> call = service.getApiSunriseAndSunset();
            call.enqueue(this);
        }


    public void onResponse(Call<ApiSunriseAndSunset> call, Response<ApiSunriseAndSunset> response) {

        ApiSunriseAndSunset list = response.body();

        log.info("Sunset :" + list.getResults().getSunset());
        log.info("Sunrise :" + list.getResults().getSunrise());
        log.info("DayLength :" + list.getResults().getDayLength());
        log.info("AstronomicalTwilightBegin :" + list.getResults().getAstronomicalTwilightBegin());
        log.info("AstronomicalTwilightEnd :" + list.getResults().getAstronomicalTwilightEnd());
        log.info("lightBegin :" + list.getResults().getCivilTwilightBegin());
        log.info("CivilTwilightEnd :" + list.getResults().getCivilTwilightEnd());
        log.info("NauticalTwilightBegin :" + list.getResults().getNauticalTwilightBegin());
        log.info("NauticalTwilightEnd :" + list.getResults().getNauticalTwilightEnd());
        log.info("SolarNoon :" + list.getResults().getSolarNoon());

        System.out.println("Sunset :" + list.getResults().getSunset());
        System.out.println("Sunrise :" + list.getResults().getSunrise());
        System.out.println("DayLength : " + list.getResults().getDayLength());
        System.out.println("AstronomicalTwilightBegin : " +list.getResults().getAstronomicalTwilightBegin());
        System.out.println("AstronomicalTwilightEnd : " +list.getResults().getAstronomicalTwilightEnd());
        System.out.println("lightBegin : " +list.getResults().getCivilTwilightBegin());
        System.out.println("CivilTwilightEnd : " +list.getResults().getCivilTwilightEnd());
        System.out.println("NauticalTwilightBegin : " +list.getResults().getNauticalTwilightBegin());
        System.out.println("NauticalTwilightEnd : " +list.getResults().getNauticalTwilightEnd());
        System.out.println("SolarNoon : " +list.getResults().getSolarNoon());

    }

    public void onFailure(Call<ApiSunriseAndSunset> call, Throwable throwable) {
        log.error("onFailure : " + throwable.getMessage());
    }
}
