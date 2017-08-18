package com.production.sidorov.ivan.weatherforecast.screen.main;

import com.production.sidorov.ivan.weatherforecast.ActivityScope;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Иван on 18.08.2017.
 */
@Module
public class MainScreenModule {

    private MainScreenView mView;

    public MainScreenModule(MainScreenView view) {
        mView = view;
    }

    @Provides
    @ActivityScope
    MainScreenView provideView(){
        return mView;
    }

    @Provides
    @ActivityScope
    Scheduler mainThreadScheduler(){
        return AndroidSchedulers.mainThread();
    }

}
