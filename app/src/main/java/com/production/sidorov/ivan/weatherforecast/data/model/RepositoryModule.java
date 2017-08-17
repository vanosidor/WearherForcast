package com.production.sidorov.ivan.weatherforecast.data.model;

import android.support.annotation.NonNull;

import com.production.sidorov.ivan.weatherforecast.data.ActivityScope;
import com.production.sidorov.ivan.weatherforecast.data.WeatherDataSource;
import com.production.sidorov.ivan.weatherforecast.data.WeatherRepository;
import com.production.sidorov.ivan.weatherforecast.network.WeatherService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Иван on 17.08.2017.
 */

@Module
public class RepositoryModule {
    @Provides
    @ActivityScope
    public WeatherDataSource provideRepository(@NonNull WeatherService weatherService){
        return new WeatherRepository(weatherService);
    }
}
