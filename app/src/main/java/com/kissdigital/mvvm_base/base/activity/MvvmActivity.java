package com.kissdigital.mvvm_base.base.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kissdigital.mvvm_base.base.viewmodel.BaseViewModel;

/**
 * Created by O10 on 24.05.2017.
 */

public abstract class MvvmActivity<T extends BaseViewModel> extends BaseActivity {

    T viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, getApp().getAppComponent().factory()).get(getViewModelType());
        if (savedInstanceState == null) {
            initViewModel();
            viewModel.onInitialized();
        }
    }

    public T getViewModel() {
        return viewModel;
    }

    protected abstract Class<T> getViewModelType();

    protected void initViewModel() {
    }
}
