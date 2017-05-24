package com.kissdigital.mvvm_base.app.di.components;

import android.arch.lifecycle.ViewModelProvider;

import com.kissdigital.mvvm_base.app.Configuration;
import com.kissdigital.mvvm_base.app.di.ApplicationScope;
import com.kissdigital.mvvm_base.app.di.modules.AppModule;

import dagger.Component;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

/**
 * Created by O10 on 24.05.2017.
 */
@Component(modules = {AppModule.class})
@ApplicationScope
public interface AppComponent {
    Configuration configuration();

    ViewModelProvider.Factory factory();

    ReactiveLocationProvider locationProvider();
}
