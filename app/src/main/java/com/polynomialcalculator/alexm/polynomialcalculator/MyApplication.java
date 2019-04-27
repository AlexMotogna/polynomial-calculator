package com.polynomialcalculator.alexm.polynomialcalculator;

import android.app.Application;
import com.orhanobut.hawk.Hawk;
import com.polynomialcalculator.alexm.polynomialcalculator.models.Polynomial;

import java.util.ArrayList;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
