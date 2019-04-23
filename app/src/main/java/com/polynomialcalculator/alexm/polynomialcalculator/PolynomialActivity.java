package com.polynomialcalculator.alexm.polynomialcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.orhanobut.hawk.Hawk;
import com.polynomialcalculator.alexm.polynomialcalculator.models.Polynomial;

import java.util.ArrayList;


public class PolynomialActivity extends AppCompatActivity {

    ArrayList<Polynomial> list;
    Polynomial polynomial;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polynomial);

        setup();
    }

    private void setup() {

        if (Hawk.contains("polynomialList")) {
            list = Hawk.get("polynomialList");
        } else {
            list = new ArrayList<>();
        }

        Intent intent = getIntent();
        position = intent.getIntExtra("pos", -1);

        if(position != -1) polynomial = list.get(position);


    }
}
