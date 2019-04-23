package com.polynomialcalculator.alexm.polynomialcalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;

import java.util.ArrayList;

public class CreatePolynomialActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_polynomial);

        setupViews();
    }

    private void setupViews() {
        recyclerView = findViewById(R.id.create_polynomial_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Pair<Integer, Integer>> integers = new ArrayList<>();
        integers.add(new Pair<>(1, 2));
        integers.add(new Pair<>(1, 2));
        integers.add(new Pair<>(1, 2));

        recyclerView.setAdapter(new CreatePolynomialAdapter(this, integers));
    }
}
