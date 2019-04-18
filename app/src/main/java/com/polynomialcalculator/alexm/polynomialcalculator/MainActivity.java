package com.polynomialcalculator.alexm.polynomialcalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.orhanobut.hawk.Hawk;
import com.polynomialcalculator.alexm.polynomialcalculator.models.Polynomial;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        FloatingActionButton floatingActionButton = findViewById(R.id.act_main_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Floating action button works", Toast.LENGTH_LONG).show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.polynomial_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Polynomial> list;
        if (Hawk.contains("polynomialList")) {
            list = Hawk.get("polynomialList");
        } else {
            list = new ArrayList<>();
        }

        PolynomialAdapter mAdapter = new PolynomialAdapter(list);

        mAdapter.setOnItemClickListener(new PolynomialAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Polynomial polynomial) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(mAdapter);
    }
}
