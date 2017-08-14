package com.production.sidorov.ivan.weatherforecast.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Иван on 03.08.2017.
 */

public class Main extends RealmObject implements Serializable {

    @SerializedName("temp")
    private double mTemp;

    @SerializedName("pressure")
    private double mPressure;

    @SerializedName("humidity")
    private double humidity;


    public int getTemp() {
        return (int)mTemp;
    }


    public int getPressure() {
        return (int)mPressure;
    }


    public int getHumidity() {
        return (int)humidity;
    }
}
