package com.polynomialcalculator.alexm.polynomialcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
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

        final Button addButton = (Button)findViewById(R.id.btn_add);
        Button multiplyButton = (Button)findViewById(R.id.btn_multiply);
        Button divideButton = (Button)findViewById(R.id.btn_divide);
        Button valueButton =  (Button)findViewById(R.id.btn_value);
        final Button solutionButton = (Button)findViewById(R.id.btn_whole_solutions);
        Button derivativeButton = (Button)findViewById(R.id.btn_derivative);
        Button antiderivativeButton = (Button)findViewById(R.id.btn_antiderivative);
        Button integrateButton = (Button)findViewById(R.id.btn_integrate);
        Button deleteButton = (Button)findViewById(R.id.btn_delete);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PolynomialActivity.this, addition(polynomial,multiplyWithConstant(polynomial, -1, 1)).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PolynomialActivity.this, multiplyWithConstant(polynomial, 2, 4).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pair<Polynomial, Polynomial> pair = division(polynomial, list.get(0)) ;
                Toast.makeText(PolynomialActivity.this, pair.second.toString(), Toast.LENGTH_LONG).show();
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
                ArrayList<Integer> solutions = polynomial.getWholeSolutions();
                StringBuilder g = new StringBuilder();
                for(int i = 0 ;i < solutions.size(); i++){
                    g.append(solutions.get(i));
                    g.append(" ");
                }

                Toast.makeText(PolynomialActivity.this, g.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        derivativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PolynomialActivity.this, polynomial.getDerivative().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        antiderivativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PolynomialActivity.this, polynomial.getOneAntiderivative(2).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        integrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PolynomialActivity.this, polynomial.integrate(0, 1).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);
                Hawk.put(Constants.POLYNOMIAL_LIST, list);
                PolynomialActivity.this.finish();
            }
        });
    }

    private Polynomial addition(Polynomial pol1, Polynomial pol2){

        Integer[] numarator = new Integer[1000];
        Integer[] numitor = new Integer[1000];
        Integer grad;

        if(pol1.getGrad() > pol2.getGrad()) grad = pol1.getGrad();
        else grad = pol2.getGrad();

        for(int i = grad; i >= 0; i--){
            if(pol1.getNumarator()[i] == null) {
                pol1.getNumarator()[i] = 0;
                pol2.getNumitor()[i] = 1;
            }

            if(pol2.getNumarator()[i] == null){
                pol2.getNumarator()[i] = 0;
                pol2.getNumitor()[i] = 1;
            }
        }

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

    private  Polynomial multiplication(Polynomial pol1, Polynomial pol2){
        Integer[] numarator = new Integer[1000];
        Integer[] numitor = new Integer[1000];
        Integer grad = pol1.getGrad() + pol2.getGrad();

        for(int i = 0; i <= grad; i++){
            numarator[i] = 0;
            numitor[i] = 1;
        }

        for(int i = 0; i <= pol1.getGrad(); i++){
            for(int j = 0; j <= pol2.getGrad(); j++) {
                int addedNuma = pol1.getNumarator()[i] * pol2.getNumarator()[j];
                int addedNumi = pol1.getNumitor()[i] * pol2.getNumitor()[j];

                numarator[i + j] = numarator[i + j] * addedNumi + numitor[i + j] * addedNuma;
                numitor[i + j] =  numitor[i + j] * addedNumi;

            }
        }

        return new Polynomial(numarator, numitor, grad);
    }

    private Polynomial multiplyWithConstant(Polynomial pol, int x, int y){

        Integer[] numa = new Integer[1000];
        Integer[] numi = new Integer[1000];

        for(int i = 0; i <= pol.getGrad(); i++){
            numa[i] = pol.getNumarator()[i] * x;
            numi[i] = pol.getNumitor()[i] * y;
        }

        return new Polynomial(numa, numi, pol.getGrad());
    }

    private Pair<Polynomial, Polynomial> division(Polynomial pol1, Polynomial pol2){

        if(pol1.getGrad() < pol2.getGrad()) {

            Integer[] numa = new Integer[1000];
            Integer[] numi = new Integer[1000];
            numa[0] = 0;
            numi[0] = 1;

            Pair<Polynomial, Polynomial> pair = new Pair<>(new Polynomial(numa, numi, 0), pol1);
            return pair;

        } else {

            if(pol1.getGrad() == pol2.getGrad()) {

                Integer[] numa = new Integer[1000];
                Integer[] numi = new Integer[1000];
                numa[0] = pol2.getNumitor()[pol2.getGrad()] * pol1.getNumarator()[pol1.getGrad()];
                numi[0] = pol2.getNumarator()[pol2.getGrad()] * pol1.getNumitor()[pol1.getGrad()];

                if(numa[0] < 0 && numi[0] < 0){
                    numa[0] *= -1;
                    numi[0] *= -1;
                }

                Pair<Polynomial, Polynomial> pair = new Pair<>(new Polynomial(numa, numi, 0), addition(pol1, multiplyWithConstant(pol2, -1 * numa[0], numi[0])) );

                return pair;

            } else {

                Integer[] numaCat = new Integer[1000];
                Integer[] numiCat = new Integer[1000];

                numaCat[pol1.getGrad() - pol2.getGrad()] = pol1.getNumarator()[pol1.getGrad()] * pol2.getNumitor()[pol2.getGrad()];
                numiCat[pol1.getGrad() - pol2.getGrad()] = pol1.getNumitor()[pol1.getGrad()] * pol2.getNumarator()[pol2.getGrad()];

                for(int i = pol1.getGrad() - pol2.getGrad() - 1; i >= 0; i--){
                    numaCat[i] = 0;
                    numiCat[i] = 1;
                }

                Polynomial catCurent = new Polynomial(numaCat, numiCat, pol1.getGrad() - pol2.getGrad());

                Polynomial polTest1 = multiplyWithConstant(multiplication(pol2, catCurent), -1 ,1);
                Polynomial polTest2 = addition(pol1, polTest1);
                Pair<Polynomial, Polynomial> pair = division(addition(pol1, multiplyWithConstant(multiplication(pol2, catCurent), -1, 1)), pol2);
                Pair<Polynomial, Polynomial> rezultat = new Pair<>(addition(catCurent, pair.first), pair.second);

                return rezultat;
            }

        }

    }


}
