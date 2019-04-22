package com.polynomialcalculator.alexm.polynomialcalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class CreatePolynomialActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button addCoefficients;

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

        recyclerView.setAdapter(new CreatePolynomialAdapter(integers));

        addCoefficients = findViewById(R.id.add_coefficients);
        addCoefficients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CreatePolynomialAdapter) Objects.requireNonNull(recyclerView.getAdapter())).isLastEmpty()) {
                    Toast.makeText(CreatePolynomialActivity.this, "you must complete last coefficients", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }
}
