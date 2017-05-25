package com.kissdigital.mvvm_base.app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.util.ArrayMap;

import com.kissdigital.mvvm_base.app.di.components.ViewModelsComponent;
import com.kissdigital.mvvm_base.ui.click.ClicksViewModel;
import com.kissdigital.mvvm_base.ui.main.MainViewModel;

import java.util.Map;
import java.util.concurrent.Callable;

import timber.log.Timber;

/**
 * Created by O10 on 24.05.2017.
 */

public class MyViewModelFactory implements ViewModelProvider.Factory {

    private final ArrayMap<Class, Callable<? extends ViewModel>> creators = new ArrayMap<>();

    public MyViewModelFactory(ViewModelsComponent viewModelsComponent) {
        creators.put(MainViewModel.class, viewModelsComponent::mainViewModel);
        creators.put(ClicksViewModel.class, viewModelsComponent::clicksViewModel);
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            Timber.d("Creating new view model of class %s", modelClass.getSimpleName());
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
