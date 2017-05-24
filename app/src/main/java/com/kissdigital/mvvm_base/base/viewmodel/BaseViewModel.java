package com.kissdigital.mvvm_base.base.viewmodel;

import android.arch.lifecycle.ViewModel;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by O10 on 24.05.2017.
 */

public class BaseViewModel extends ViewModel {

    private BehaviorSubject<Object> viewModelClearedSubject = BehaviorSubject.create();

    @Override
    protected void onCleared() {
        super.onCleared();
        viewModelClearedSubject.onNext(new Object());
    }

    public void onInitialized() {

    }
}
