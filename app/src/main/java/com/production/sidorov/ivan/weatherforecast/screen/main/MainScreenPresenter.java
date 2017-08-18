package com.production.sidorov.ivan.weatherforecast.screen.main;

import com.production.sidorov.ivan.weatherforecast.data.repository.WeatherDataSource;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Scheduler;
import rx.Subscription;

import static com.production.sidorov.ivan.weatherforecast.screen.main.WeatherActivity.CITIES;

/**
 * Created by Иван on 18.08.2017.
 */

public class MainScreenPresenter {

    private WeatherDataSource  weatherRepository;
    private MainScreenView mainScreenView;
    private final Scheduler mainScheduler;

    @Inject
    public MainScreenPresenter(WeatherDataSource weatherRepository, MainScreenView mainScreenView,Scheduler mainScheduler) {
        this.weatherRepository = weatherRepository;
        this.mainScreenView = mainScreenView;
        this.mainScheduler = mainScheduler;
    }

    Subscription loadWeather(){
        return weatherRepository
                .getWeather(CITIES)
                .delay(3000L, TimeUnit.MILLISECONDS) //imitation slow connection
                .doOnSubscribe(mainScreenView::showLoadingView)
                .doAfterTerminate(mainScreenView::hideLoadingView)
                .observeOn(mainScheduler)
                .subscribe(mainScreenView::showWeather, mainScreenView::showError);
    }
}
