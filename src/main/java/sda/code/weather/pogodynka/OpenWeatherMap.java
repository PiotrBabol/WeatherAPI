package sda.code.weather.pogodynka;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sda.code.weather.model.TheWeather;


public interface OpenWeatherMap {
    @GET("data/2.5/weather")
        //patch
    Call<TheWeather> currentByCity(
            //query params
            @Query("q") String cityName,
            @Query("appid") String appiKey,
            @Query("units") String units,
            @Query("lang") String lang
    );

    @GET("data/2.5/weather")
        //patch
    Call<TheWeather> currentByGeo(
            //query params
            @Query("lat") Double lat,
            @Query("lon") Double lon,
            @Query("appid") String appiKey,
            @Query("units") String units,
            @Query("lang") String lang

    );

    /*@GET("data/2.5/weather")
        //patch
    CompletableFuture<TheWeather> currentByCity(
            //query params
            @Query("q") String cityName,
            @Query("appid") String appiKey,
            @Query("units") String units,
            @Query("lang") String lang
    );

    @GET("data/2.5/weather")
        //patch
    CompletableFuture<TheWeather> currentByGeo(
            //query params
            @Query("lat") Double lat,
            @Query("lon") Double lon,
            @Query("appid") String appiKey,
            @Query("units") String units,
            @Query("lang") String lang
*/;
}
