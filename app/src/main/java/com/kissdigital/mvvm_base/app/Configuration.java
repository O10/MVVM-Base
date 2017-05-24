package com.kissdigital.mvvm_base.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by O10 on 24.05.2017.
 */

public class Configuration {

    private final SharedPreferences sharedPreferences;

    public Configuration(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
