package com.kissdigital.mvvm_base.ui.main;

import com.kissdigital.mvvm_base.ui.di.ActivityScope;
import com.kissdigital.mvvm_base.ui.di.RxPermissionsModule;
import com.kissdigital.mvvm_base.ui.main.MainActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Component;

/**
 * Created by O10 on 24.05.2017.
 */
@ActivityScope
@Component(modules = RxPermissionsModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
