package com.production.sidorov.ivan.weatherforecast.data.repository;

import com.production.sidorov.ivan.weatherforecast.network.WeatherService;
import com.production.sidorov.ivan.weatherforecast.screen.main.WeatherActivity;
import com.production.sidorov.ivan.weatherforecast.data.model.Weather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Иван on 04.08.2017.
 */

public class WeatherRepository implements WeatherDataSource {

    private WeatherService mWeatherService;

    @Inject
    public WeatherRepository(WeatherService weatherService) {
        this.mWeatherService = weatherService;
    }

    @Override
    public Observable<List<Weather>> getWeather(List<String> cityNames) {

        List<Observable<Weather>> weatherObservables = new ArrayList<>();

        if (cityNames.size() != 0) {
            for (int i = 0; i < cityNames.size(); i++) {
                weatherObservables.add(mWeatherService.getWeather(cityNames.get(i), WeatherActivity.API_KEY));
            }
        } else return null;

        return Observable.zip(weatherObservables, Arrays::asList)
                .map(this::castObjectToWeather)
                .compose(new WeatherCacheTransformer())
                .subscribeOn(Schedulers.io());
    }

    private List<Weather> castObjectToWeather(List<Object> objects){
        List<Weather> weathers = new ArrayList<>();
        for (int j = 0; j < objects.size(); j++) {
            weathers.add((Weather)(objects.get(j)));
        }
        return weathers;
    }

    private class WeatherCacheTransformer implements Observable.Transformer<List<Weather>, List<Weather>> {

        @Override
        public Observable<List<Weather>> call(Observable<List<Weather>> weathersObservable) {
            Timber.d("In weather cache transformer");
            return weathersObservable
                    .flatMap(saveInRealm)
                    .onErrorResumeNext(loadFromRealm);
        }
    }

    private final Func1<List<Weather>, Observable<List<Weather>>> saveInRealm = weathers -> {
        Timber.d("Save in Realm: ",weathers.size());
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.delete(Weather.class);
            realm.insert(weathers);
        });
        return Observable.just(weathers);
    };

    private final Func1 <Throwable, Observable<List<Weather>>> loadFromRealm  = throwable -> {
        Timber.d(throwable,"Network error occurred, try download from realm " + throwable.getMessage());
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Weather> results = realm.where(Weather.class).findAll();
        return Observable.just(realm.copyFromRealm(results));
    };

}
