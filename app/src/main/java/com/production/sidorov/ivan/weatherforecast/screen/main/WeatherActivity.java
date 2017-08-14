package com.production.sidorov.ivan.weatherforecast.screen.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.production.sidorov.ivan.weatherforecast.R;
import com.production.sidorov.ivan.weatherforecast.screen.main.adapter.ForecastAdapter;
import com.production.sidorov.ivan.weatherforecast.data.model.Weather;
import com.production.sidorov.ivan.weatherforecast.data.WeatherRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class WeatherActivity extends AppCompatActivity implements ForecastAdapter.ListItemClickListener {

    public static final List<String> CITIES = Arrays.asList("Moscow","London","New York","Berlin","Madrid","Dublin");
    public static final String API_KEY = "64294a2eb3771a2da7e7b2218c6494fc";


    @BindView(R.id.weather_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar_loading)
    ProgressBar mLoadingProgressBar;

    ForecastAdapter mForecastAdapter;
    private Subscription mWeatherSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        mForecastAdapter = new ForecastAdapter(this, this);
        recyclerView.setAdapter(mForecastAdapter);


        WeatherRepository weatherRepository = WeatherRepository.getInstance();

        mWeatherSubscription = weatherRepository
                .getWeather(CITIES)
                .delay(3000L, TimeUnit.MILLISECONDS) //imitation slow connection
                .doOnSubscribe(this::showLoadingView)
                .doAfterTerminate(this::hideLoadingView)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showWeather, this::showError);


        /* test only
        mWeatherSubscription = ApiFactory
                .getWeatherService()
                .getWeather("Moscow",API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> Timber.d(weather.toString()), this::showError);
                */

    }

    @Override
    protected void onPause() {
        if (mWeatherSubscription != null)
            mWeatherSubscription.unsubscribe();
        super.onPause();

    }

    public void showLoadingView() {
        mLoadingProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoadingView() {
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onListItemClicked(int itemIndex) {
        Toast.makeText(this, "Item clicked " + itemIndex, Toast.LENGTH_SHORT).show();
    }

    private void showWeather(List<Weather> weathers) {
        Log.d(WeatherActivity.class.getSimpleName(), "Downloading OK");
        mForecastAdapter.setWeatherToAdapter(weathers);
    }

    private void showError(Throwable throwable) {
        throwable.printStackTrace();
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(this, "Error downloading occured", Toast.LENGTH_SHORT).show();
    }
}