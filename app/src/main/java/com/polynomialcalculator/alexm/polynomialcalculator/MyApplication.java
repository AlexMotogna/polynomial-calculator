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

        ArrayList<Polynomial> list = new ArrayList<>();

        ArrayList<Integer> numarator = new ArrayList<>();
        numarator.add(2);
        numarator.add(4);
        numarator.add(4);
        numarator.add(4);
        numarator.add(4);
        numarator.add(4);
        numarator.add(4);
        numarator.add(2);
        numarator.add(4);
        numarator.add(4);
        numarator.add(4);
        numarator.add(4);
        numarator.add(4);
        numarator.add(4);

        ArrayList<Integer> numitor = new ArrayList<>();
        numitor.add(1);
        numitor.add(3);
        numitor.add(4);
        numitor.add(4);
        numitor.add(4);
        numitor.add(4);
        numitor.add(4);
        numitor.add(1);
        numitor.add(3);
        numitor.add(4);
        numitor.add(4);
        numitor.add(4);
        numitor.add(4);
        numitor.add(4);

        Polynomial p = new Polynomial(numarator, numitor, 12);

        for (int i = 0; i < 20; i++) {
            list.add(p);
        }

        Hawk.put("polynomialList", list);
    }
}
