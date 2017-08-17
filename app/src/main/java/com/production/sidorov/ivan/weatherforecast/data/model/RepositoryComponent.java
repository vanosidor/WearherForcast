package com.production.sidorov.ivan.weatherforecast.data.model;

import com.production.sidorov.ivan.weatherforecast.data.ActivityScope;
import com.production.sidorov.ivan.weatherforecast.data.WeatherDataSource;
import com.production.sidorov.ivan.weatherforecast.network.NetworkComponent;
import com.production.sidorov.ivan.weatherforecast.network.NetworkModule;
import com.production.sidorov.ivan.weatherforecast.screen.main.WeatherActivity;

import dagger.Component;

/**
 * Created by Иван on 16.08.2017.
 */
@ActivityScope
@Component(dependencies = NetworkComponent.class, modules = RepositoryModule.class)
public interface RepositoryComponent {
    void inject(WeatherActivity weatherActivity);
}
