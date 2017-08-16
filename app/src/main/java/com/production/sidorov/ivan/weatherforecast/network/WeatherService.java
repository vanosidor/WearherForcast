package com.production.sidorov.ivan.weatherforecast.network;

import com.production.sidorov.ivan.weatherforecast.data.model.Weather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Иван on 12.07.2017.
 */

public interface WeatherService {
    @GET("weather?units=metric")
    Observable<Weather> getWeather(@Query("q") String city, @Query("appid") String apiKey);
}
