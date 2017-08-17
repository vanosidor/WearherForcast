package com.production.sidorov.ivan.weatherforecast.network;

import com.production.sidorov.ivan.weatherforecast.data.WeatherRepository;
import com.production.sidorov.ivan.weatherforecast.data.model.Weather;
import com.production.sidorov.ivan.weatherforecast.screen.main.WeatherActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Иван on 08.08.2017.
 */

@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {

    WeatherService weatherService();

}
