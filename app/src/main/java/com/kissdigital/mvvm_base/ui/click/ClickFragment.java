package com.kissdigital.mvvm_base.ui.click;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.kissdigital.mvvm_base.R;
import com.kissdigital.mvvm_base.base.fragment.MvvmFragment;

import butterknife.BindView;

/**
 * Created by O10 on 25.05.2017.
 */

public class ClickFragment extends MvvmFragment<ClicksViewModel> {

    public static ClickFragment newInstance() {

        Bundle args = new Bundle();

        ClickFragment fragment = new ClickFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.button_clicks)
    Button clicksButton;

    @BindView(R.id.clicks_num)
    TextView clicksNum;

    @Override
    protected Class<ClicksViewModel> getViewModelType() {
        return ClicksViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_clicks;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RxView.clicks(clicksButton)
                .subscribe(o -> {
                    getViewModel().buttonClicked();
                });

        getViewModel().clicksNumObservable()
                .compose(bindToLifecycle())
                .subscribe(integer -> clicksNum.setText(String.valueOf(integer)));
    }
}
