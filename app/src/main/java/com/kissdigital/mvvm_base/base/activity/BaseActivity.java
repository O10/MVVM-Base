package com.kissdigital.mvvm_base.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kissdigital.mvvm_base.app.MyApp;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by O10 on 24.05.2017.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initDagger();
    }

    protected abstract int getLayoutId();

    protected void initDagger() {

    }

    public MyApp getApp() {
        return (MyApp) getApplication();
    }
}
