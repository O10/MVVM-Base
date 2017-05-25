package com.kissdigital.mvvm_base.app.di.components;

import com.kissdigital.mvvm_base.ui.click.ClicksViewModel;
import com.kissdigital.mvvm_base.ui.main.MainViewModel;

import dagger.Subcomponent;

/**
 * Created by O10 on 24.05.2017.
 */
@Subcomponent
public interface ViewModelsComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelsComponent build();
    }

    MainViewModel mainViewModel();

    ClicksViewModel clicksViewModel();
}
