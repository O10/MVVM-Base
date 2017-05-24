package com.kissdigital.mvvm_base.ui.main;

import android.annotation.SuppressLint;
import android.location.Location;

import com.google.android.gms.location.LocationRequest;
import com.kissdigital.mvvm_base.base.viewmodel.BaseViewModel;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

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

    private Observable<ActivityEvent> lifecycle;

    @Inject
    public MainViewModel(ReactiveLocationProvider reactiveLocationProvider) {
        this.rxLocation = reactiveLocationProvider;
    }

    public void bindLifecycle(Observable<ActivityEvent> lifecycle) {
        this.lifecycle = lifecycle;
    }

    @SuppressLint("MissingPermission")
    public void permissionsGranted() {

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000);

        final Observable<Location> locationObservable = RxJavaInterop.toV2Observable(rxLocation.getUpdatedLocation(locationRequest));
        locationObservable
                .compose(RxLifecycle.bindUntilEvent(lifecycle, ActivityEvent.STOP))
                .subscribe(currentLocation::onNext);
    }

    public Observable<Location> locationObservable() {
        return currentLocation.hide();
    }

}
