package com.kissdigital.mvvm_base.ui.di;

import com.kissdigital.mvvm_base.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by O10 on 24.05.2017.
 */
@Component(modules = RxPermissionsModule.class)
@ActivityScope
public interface GeneralActivityComponent {
    void inject(MainActivity activity);
}
