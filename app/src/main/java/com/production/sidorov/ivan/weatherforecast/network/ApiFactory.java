package com.production.sidorov.ivan.weatherforecast.network;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Иван on 12.07.2017.
 */

public class ApiFactory {


    public static final String API_ENDPOINT = "http://api.openweathermap.org/data/2.5/";


    private static OkHttpClient sOkHttpClient;

    private static WeatherService sWeatherService;

    @NonNull
    public static WeatherService getWeatherService()

    {
        WeatherService service = sWeatherService;

        if (service == null) {
            synchronized (ApiFactory.class) {

                service = sWeatherService;
                if (service == null) {
                    service = sWeatherService = createService();
                }
            }
        }
        return service;
    }

    @NonNull
    private static WeatherService createService() {
        return new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .client(getOkHTTPClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService.class);
    }

    @NonNull
    private static OkHttpClient getOkHTTPClient() {

        OkHttpClient client = sOkHttpClient;

        if(client == null){
            synchronized (ApiFactory.class){
                client =sOkHttpClient;
                if(client == null){

                    client = sOkHttpClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }



}
