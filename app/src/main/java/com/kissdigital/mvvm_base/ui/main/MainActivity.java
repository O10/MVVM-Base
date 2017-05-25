package com.kissdigital.mvvm_base.ui.main;

import android.Manifest;
import android.widget.TextView;

import com.kissdigital.mvvm_base.R;
import com.kissdigital.mvvm_base.base.activity.MvvmActivity;
import com.kissdigital.mvvm_base.ui.di.RxPermissionsModule;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

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
        DaggerMainActivityComponent.builder()
                .rxPermissionsModule(new RxPermissionsModule(this))
                .build().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION)
                .filter(granted -> granted)
                .flatMap(ignored -> getViewModel().locationObservable())
                .compose(RxLifecycle.bindUntilEvent(lifecycle(), ActivityEvent.STOP))
                .subscribe(location -> {
                    mainText.setText(location.toString());
                }, Timber::e);
    }
}
