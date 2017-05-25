package com.kissdigital.mvvm_base.ui.click;

import com.kissdigital.mvvm_base.base.viewmodel.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by O10 on 25.05.2017.
 */

public class ClicksViewModel extends BaseViewModel {

    @Inject
    public ClicksViewModel() {
    }

    private BehaviorSubject<Integer> clicksSubject = BehaviorSubject.createDefault(0);

    public void buttonClicked() {
        clicksSubject.onNext(clicksSubject.getValue() + 1);
    }

    public Observable<Integer> clicksNumObservable() {
        return clicksSubject.hide();
    }
}
