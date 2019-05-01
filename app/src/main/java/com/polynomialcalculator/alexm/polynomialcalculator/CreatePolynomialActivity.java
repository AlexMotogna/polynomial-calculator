package com.polynomialcalculator.alexm.polynomialcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import com.orhanobut.hawk.Hawk;
import com.polynomialcalculator.alexm.polynomialcalculator.models.Polynomial;

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
        Button saveButton = findViewById(R.id.save_pol);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Integer[] a = new Integer[1000];
        Integer[] b = new Integer[1000];
        ArrayList<Integer> c = new ArrayList<>();
        recyclerView.setAdapter(new CreatePolynomialAdapter(this, a, b, c));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Polynomial pol = ((CreatePolynomialAdapter) recyclerView.getAdapter()).getFinalPolynomial();
                pol.simplify();
                ArrayList<Polynomial> list = Hawk.get(Constants.POLYNOMIAL_LIST);
                list.add(pol);
                Hawk.put(Constants.POLYNOMIAL_LIST,list);
                CreatePolynomialActivity.this.finish();
            }
        });
    }
}
