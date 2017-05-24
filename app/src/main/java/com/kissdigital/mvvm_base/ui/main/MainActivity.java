package com.kissdigital.mvvm_base.ui.main;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.kissdigital.mvvm_base.R;
import com.kissdigital.mvvm_base.base.activity.MvvmActivity;
import com.kissdigital.mvvm_base.ui.di.DaggerGeneralActivityComponent;
import com.kissdigital.mvvm_base.ui.di.RxPermissionsModule;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by O10 on 24.05.2017.
 */

public class MainActivity extends MvvmActivity<MainViewModel> {

    @BindView(R.id.main_text)
    TextView mainText;

    @Inject
    RxPermissions rxPermissions;

    @Override
    protected Class<MainViewModel> getViewModelType() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDagger() {
        super.initDagger();
        DaggerGeneralActivityComponent.builder()
                .rxPermissionsModule(new RxPermissionsModule(this))
                .build().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().locationObservable()
                .compose(bindToLifecycle())
                .subscribe(location -> {
                    mainText.setText(location.toString());
                });

        getViewModel().bindLifecycle(lifecycle());
    }

    @Override
    protected void onStart() {
        super.onStart();
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
                .filter(granted -> granted)
                .subscribe(granted -> {
                    getViewModel().permissionsGranted();
                }, Timber::e);
    }
}
