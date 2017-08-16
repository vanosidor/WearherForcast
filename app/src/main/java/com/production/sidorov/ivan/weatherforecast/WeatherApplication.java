package com.production.sidorov.ivan.weatherforecast;

import android.app.Application;


import com.production.sidorov.ivan.weatherforecast.network.DaggerNetworkComponent;
import com.production.sidorov.ivan.weatherforecast.network.NetworkComponent;
import com.production.sidorov.ivan.weatherforecast.network.NetworkModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import timber.log.Timber;

/**
 * Created by Иван on 12.07.2017.
 */

public class WeatherApplication extends Application {

    private NetworkComponent mNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mNetworkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(getString(R.string.base_url)))
                .build();


        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder(this)
                .rxFactory(new RealmObservableFactory())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public NetworkComponent getNetworkComponent() {
        return mNetworkComponent;
    }
}
