package com.polynomialcalculator.alexm.polynomialcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
        if (Hawk.contains(Constants.POLYNOMIAL_LIST)) {
            list = Hawk.get(Constants.POLYNOMIAL_LIST);
        } else {
            list = new ArrayList<>();
        }

        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);

        polynomial = list.get(position);

        TextView polynomialDisplay = (TextView)findViewById(R.id.polynomial_tv);
        polynomialDisplay.setText(polynomial.toString());

        Button addButton = (Button)findViewById(R.id.btn_add);
        Button multiplyButton = (Button)findViewById(R.id.btn_multiply);
        Button divideButton = (Button)findViewById(R.id.btn_divide);
        Button valueButton =  (Button)findViewById(R.id.btn_value);
        Button solutionButton = (Button)findViewById(R.id.btn_whole_solutions);
        Button derivativeButton = (Button)findViewById(R.id.btn_derivative);
        Button antiderivativeButton = (Button)findViewById(R.id.btn_antiderivative);
        Button integrateButton = (Button)findViewById(R.id.btn_integrate);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        valueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        solutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        derivativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        antiderivativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        integrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private Polynomial addition(Polynomial pol1, Polynomial pol2){

        Integer[] numarator = new Integer[1000];
        Integer[] numitor = new Integer[1000];
        Integer grad;

        if(pol1.getGrad() > pol2.getGrad()) grad = pol1.getGrad();
        else grad = pol2.getGrad();

        for(int i = 0; i <= grad; i++) {
            numarator[i] = pol1.getNumarator()[i] * pol2.getNumitor()[i] + pol2.getNumarator()[i] * pol1.getNumitor()[i];
            numitor[i] = pol1.getNumitor()[i] * pol2.getNumitor()[i];
        }

        Integer corection = 0;

        for(int i = 0; i <= grad; i++){
            if(numarator[i] == null){
                numarator[i] = 0;
                numitor[i] = 1;
            } else {
                if(numarator[i] == 0) numitor[i] = 1;
                else corection = i;
            }
        }

        return new Polynomial(numarator, numitor, corection);
    }

}
