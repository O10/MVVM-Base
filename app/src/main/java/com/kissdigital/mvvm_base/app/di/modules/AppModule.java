package com.kissdigital.mvvm_base.app.di.modules;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.kissdigital.mvvm_base.app.Configuration;
import com.kissdigital.mvvm_base.app.MyViewModelFactory;
import com.kissdigital.mvvm_base.app.di.ApplicationScope;
import com.kissdigital.mvvm_base.app.di.components.ViewModelsComponent;

import dagger.Module;
import dagger.Provides;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

/**
 * Created by O10 on 24.05.2017.
 */
@Module(subcomponents = ViewModelsComponent.class)
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    Configuration configuration() {
        return new Configuration(context);
    }

    @Provides
    @ApplicationScope
    ReactiveLocationProvider reactiveLocationProvider() {
        return new ReactiveLocationProvider(context);
    }

    @Provides
    @ApplicationScope
    ViewModelProvider.Factory factory(ViewModelsComponent.Builder builder) {
        return new MyViewModelFactory(builder.build());
    }
}
