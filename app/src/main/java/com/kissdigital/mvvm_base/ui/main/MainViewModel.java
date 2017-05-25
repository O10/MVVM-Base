package com.kissdigital.mvvm_base.ui.main;

import android.annotation.SuppressLint;
import android.location.Location;

import com.google.android.gms.location.LocationRequest;
import com.kissdigital.mvvm_base.base.viewmodel.BaseViewModel;

import javax.inject.Inject;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;


/**
 * Created by O10 on 24.05.2017.
 */

public class MainViewModel extends BaseViewModel {

    private final ReactiveLocationProvider rxLocation;
    private final BehaviorSubject<Location> currentLocation = BehaviorSubject.create();

    @Inject
    public MainViewModel(ReactiveLocationProvider reactiveLocationProvider) {
        this.rxLocation = reactiveLocationProvider;
    }

    @SuppressLint("MissingPermission")
    public Observable<Location> locationObservable() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000);

        return RxJavaInterop.toV2Observable(rxLocation.getUpdatedLocation(locationRequest))
                .doOnNext(currentLocation::onNext)
                .mergeWith(currentLocation.take(1))
                .distinctUntilChanged();
    }

}
