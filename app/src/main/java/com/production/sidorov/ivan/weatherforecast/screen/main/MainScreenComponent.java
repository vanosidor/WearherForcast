package com.production.sidorov.ivan.weatherforecast.screen.main;

import com.production.sidorov.ivan.weatherforecast.ActivityScope;
import com.production.sidorov.ivan.weatherforecast.data.RepositoryComponent;
import com.production.sidorov.ivan.weatherforecast.network.NetworkComponent;

import dagger.Component;

/**
 * Created by Иван on 18.08.2017.
 */
@ActivityScope
@Component(dependencies = RepositoryComponent.class,modules = MainScreenModule.class)
public interface MainScreenComponent {

    void inject (WeatherActivity weatherActivity);
}
