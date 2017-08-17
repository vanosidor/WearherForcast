package com.production.sidorov.ivan.weatherforecast.data.repository;

import com.production.sidorov.ivan.weatherforecast.data.model.Weather;

import java.util.List;

import rx.Observable;

/**
 * Created by Иван on 04.08.2017.
 */

public interface WeatherDataSource {

     Observable <List<Weather>> getWeather(List<String> cityNames);

}
