package com.production.sidorov.ivan.weatherforecast.network;

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
