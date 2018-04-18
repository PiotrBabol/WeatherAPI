package sda.code.weather.pogodynka;

import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sda.code.weather.model.TheWeather;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Pogodynka {
    private static final String sampleWeather = "{\"coord\":{\"lon\":19.47,\"lat\":51.75},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":280.15,\"pressure\":1026,\"humidity\":52,\"temp_min\":280.15,\"temp_max\":280.15},\"visibility\":10000,\"wind\":{\"speed\":4.6,\"deg\":110},\"clouds\":{\"all\":0},\"dt\":1523084400,\"sys\":{\"type\":1,\"id\":5358,\"message\":0.0295,\"country\":\"PL\",\"sunrise\":1523073801,\"sunset\":1523121964},\"id\":3093133,\"name\":\"Lodz\",\"cod\":200}";

    public static void main(String[] args) throws IOException {
        //wrzozec projektowy builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(Java8CallAdapterFactory.create())// dodatek z adapterem
                .build();

        //instacja naszego api pogodowego stworzana za pomoca retrofita
        OpenWeatherMap openWeatherMap =
                retrofit.create(OpenWeatherMap.class);

        /*Call<TheWeather> forcast = openWeatherMap.currentByCity(
                "Piotrkow Trybunalski,pl",
                "1c787a838dea8e660fe2505522598fd7",
                "metric",
                "pl"
        );

        Call<TheWeather> forcastByLonLat = openWeatherMap.currentByGeo(
                45.583333,
                9.266667,
                "1c787a838dea8e660fe2505522598fd7",
                "metric",
                "pl"
        );*/

//        while (true) {
            /*CompletableFuture<TheWeather> forcast = openWeatherMap.currentByCity(
                    "Piotrkow Trybunalski,pl",
                    "1c787a838dea8e660fe2505522598fd7",
                    "metric",
                    "pl"
            );

            CompletableFuture<TheWeather> forcastByLonLat = openWeatherMap.currentByGeo(
                    45.583333,
                    9.266667,
                    "1c787a838dea8e660fe2505522598fd7",
                    "metric",
                    "pl"
            );*/

        Call<TheWeather> forcast1 = new RequestBuilder("1c787a838dea8e660fe2505522598fd7").byCity("łódź,pl").build();
        Call<TheWeather> forcast2 = new RequestBuilder("1c787a838dea8e660fe2505522598fd7").byCoor(45.583333, 9.266667).build();


        pobierzIWyswietl(forcast1, "by name");

        pobierzIWyswietl(forcast2, "by coor");

        System.out.println("Pogodynka");
        TheWeather pogoda = new Gson().fromJson(sampleWeather, TheWeather.class);
        System.out.println(pogoda.getWeather());
        System.out.println(pogoda.getName());
        System.out.println(pogoda.getMain().getTempMax());
//        }
    }

    private static void pobierzIWyswietl(Call<TheWeather> forcast, String s) throws IOException {
//        bez lambdy
//        forcast
//                .handle(new BiFunction<TheWeather, Throwable, Object>() {
//                    @Override
//                    public Object apply(TheWeather theWeather, Throwable throwable) {
//                        if(throwable!= null){
//                            System.out.println(throwable.getMessage());
//                        }
//                        else{
//                            System.out.println(theWeather.getWeather());
//                            System.out.println(theWeather.getName());
//                            System.out.println(theWeather.getMain().getTemp());
//                        }
//                        return null;
//                    }
//                });

//        z lambda
        /*forcast.handle((theWeather, throwable) -> {
            System.out.println(Thread.currentThread().getName() + "、ヽ｀、ヽ｀°˖✧◝(⁰▿⁰)◜✧˖°、ヽ｀、ヽ｀个o(･_･｡)｀ヽ、｀ヽ、");
            if (throwable != null) {
                System.out.println(throwable.getMessage());
            } else {
                System.out.println(theWeather.getWeather());
                System.out.println(theWeather.getName());
                System.out.println(theWeather.getMain().getTemp());
            }
            return null;
        });*/


//        bez comletableFuture tylko z call i z stringiem s
        Response<TheWeather> response = forcast.execute(); //wykonaj sie

        if (response.code() == 200) {
            System.out.println(s);
            TheWeather pogodaResponce = response.body();
            System.out.println("Kod :" + response.code());
            System.out.println(pogodaResponce.getName());
            System.out.println(pogodaResponce.getWeather());
        } else {
            response.errorBody().string();
        }
    }
}
