package com.kissdigital.mvvm_base.base.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kissdigital.mvvm_base.base.viewmodel.BaseViewModel;

/**
 * Created by O10 on 25.05.2017.
 */

public abstract class MvvmFragment<T extends BaseViewModel> extends BaseFragment {

    T viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, getApp().getAppComponent().factory()).get(getViewModelType());
        if (!viewModel.isViewModelInitialized()) {
            initViewModel();
            viewModel.onInitialized();
        }
    }

    protected abstract Class<T> getViewModelType();

    public T getViewModel() {
        return viewModel;
    }

    protected void initViewModel() {

    }
}
