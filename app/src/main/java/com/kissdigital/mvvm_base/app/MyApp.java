package com.kissdigital.mvvm_base.app;

import android.app.Application;

import com.kissdigital.mvvm_base.BuildConfig;
import com.kissdigital.mvvm_base.app.di.components.AppComponent;
import com.kissdigital.mvvm_base.app.di.components.DaggerAppComponent;
import com.kissdigital.mvvm_base.app.di.modules.AppModule;

import timber.log.Timber;

/**
 * Created by O10 on 24.05.2017.
 */

public class MyApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initTimber();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
