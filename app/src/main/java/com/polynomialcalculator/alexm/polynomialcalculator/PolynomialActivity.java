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
        Intent intent = getIntent();
        polynomial = (Polynomial) intent.getSerializableExtra("polynomialInstance");
    }
}
