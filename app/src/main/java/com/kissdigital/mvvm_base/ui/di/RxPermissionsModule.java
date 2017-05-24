package com.kissdigital.mvvm_base.ui.di;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;

/**
 * Created by O10 on 24.05.2017.
 */
@Module
public class RxPermissionsModule {
    private final Activity activity;

    public RxPermissionsModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    RxPermissions rxPermissions() {
        return new RxPermissions(activity);
    }
}
