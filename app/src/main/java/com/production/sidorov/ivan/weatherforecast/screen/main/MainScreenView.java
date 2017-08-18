package com.production.sidorov.ivan.weatherforecast.screen.main;

import com.production.sidorov.ivan.weatherforecast.data.model.Weather;

import java.util.List;

/**
 * Created by Иван on 18.08.2017.
 */

public interface MainScreenView {

    void showWeather(List<Weather> weathers);

    void showError(Throwable throwable);

    void showLoadingView();

    void hideLoadingView();
}
