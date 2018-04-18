package sda.code.weather.pogodynka;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sda.code.weather.model.TheWeather;

public class RequestBuilder {
    private final String apiKey;
    private Retrofit retrofit;
    private OpenWeatherMap openWeatherMap;
    private Call<TheWeather> forcast;


    public RequestBuilder(String apiKey) {
        this.apiKey = apiKey;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openWeatherMap =
                retrofit.create(OpenWeatherMap.class);

    }

    public RequestBuilder byCity(String s) {
        forcast = openWeatherMap.currentByCity("s", apiKey, "metric", "pl");
        return this;
    }

    public RequestBuilder byCoor(double v, double v1) {
        forcast = openWeatherMap.currentByGeo(v, v1, apiKey, "metric", "pl");
        return this;
    }

    public Call<TheWeather> build() {
        return forcast;
    }
}
