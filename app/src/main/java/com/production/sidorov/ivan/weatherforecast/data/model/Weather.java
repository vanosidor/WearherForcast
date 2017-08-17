package com.production.sidorov.ivan.weatherforecast.data.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Иван on 03.08.2017.
 */

public class Weather extends RealmObject implements Serializable {

    @SerializedName("name")
    private String mCityName;

    @SerializedName("main")
    private Main mMain;

    @SerializedName("wind")
    private Wind mWind;

    @NonNull
    public String getName() {
        return mCityName;
    }

    public void setName(@NonNull String name) {
        mCityName = name;
    }

    @NonNull
    public Main getMain() {
        return mMain;
    }

    public void setMain(Main main) {
        this.mMain = main;
    }

    @NonNull
    public Wind getWind() {
        return mWind;
    }

    public void setWind(Wind wind) {
        this.mWind = wind;
    }
}
