package com.production.sidorov.ivan.weatherforecast.data;

import com.production.sidorov.ivan.weatherforecast.data.repository.WeatherDataSource;
import com.production.sidorov.ivan.weatherforecast.network.NetworkComponent;

import dagger.Component;

/**
 * Created by Иван on 16.08.2017.
 */
@RepositoryScope
@Component(dependencies = NetworkComponent.class, modules = RepositoryModule.class)
public interface RepositoryComponent {

    WeatherDataSource repository();
}
