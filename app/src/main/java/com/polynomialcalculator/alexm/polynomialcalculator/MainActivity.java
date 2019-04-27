package com.polynomialcalculator.alexm.polynomialcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

    ArrayList<Polynomial> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        setupRecyclerView();

        FloatingActionButton floatingActionButton = findViewById(R.id.act_main_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent myIntent = new Intent(MainActivity.this, CreatePolynomialActivity.class);
                        MainActivity.this.startActivity(myIntent);
                    }
                }, 100);
            }
        });

        recyclerView = findViewById(R.id.polynomial_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PolynomialAdapter mAdapter = new PolynomialAdapter(list);

        mAdapter.setOnItemClickListener(new PolynomialAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Polynomial polynomial) {
                //Intent myIntent = new Intent(MainActivity.this, PolynomialActivity.class);
                //myIntent.putExtra("pos", position);
                //MainActivity.this.startActivity(myIntent);
            }
        });

        recyclerView.setAdapter(mAdapter);
    }

    private void setupRecyclerView() {
        if (Hawk.contains(Constants.POLYNOMIAL_LIST)) {
            list = Hawk.get(Constants.POLYNOMIAL_LIST);
        } else {
            list = new ArrayList<>();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setup();
    }
}
