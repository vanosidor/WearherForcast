package com.production.sidorov.ivan.weatherforecast.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Иван on 03.08.2017.
 */

public class Wind extends RealmObject implements Serializable {

    @SerializedName("speed")
    private double mSpeed;


    public int getSpeed() {
        return (int)mSpeed;
    }
}
