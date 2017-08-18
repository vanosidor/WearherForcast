package com.production.sidorov.ivan.weatherforecast.data;

import android.support.annotation.NonNull;

import com.production.sidorov.ivan.weatherforecast.ActivityScope;
import com.production.sidorov.ivan.weatherforecast.data.repository.WeatherDataSource;
import com.production.sidorov.ivan.weatherforecast.data.repository.WeatherRepository;
import com.production.sidorov.ivan.weatherforecast.network.WeatherService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Иван on 17.08.2017.
 */

@Module
public class RepositoryModule {
    @Provides
    @RepositoryScope
    public WeatherDataSource provideRepository(@NonNull WeatherService weatherService){
        return new WeatherRepository(weatherService);
    }
}
