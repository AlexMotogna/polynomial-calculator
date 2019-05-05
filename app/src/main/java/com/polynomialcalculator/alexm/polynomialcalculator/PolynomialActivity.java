package com.polynomialcalculator.alexm.polynomialcalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
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

        final TextView polynomialDisplay = (TextView) findViewById(R.id.polynomial_tv);
        polynomialDisplay.setText(polynomial.toString());

        final Button addButton = (Button) findViewById(R.id.btn_add);
        Button multiplyButton = (Button) findViewById(R.id.btn_multiply);
        Button divideButton = (Button) findViewById(R.id.btn_divide);
        Button valueButton = (Button) findViewById(R.id.btn_value);
        final Button solutionButton = (Button) findViewById(R.id.btn_whole_solutions);
        Button derivativeButton = (Button) findViewById(R.id.btn_derivative);
        Button antiderivativeButton = (Button) findViewById(R.id.btn_antiderivative);
        Button integrateButton = (Button) findViewById(R.id.btn_integrate);
        Button deleteButton = (Button) findViewById(R.id.btn_delete);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PolynomialActivity.this);
                LayoutInflater inflater = LayoutInflater.from(PolynomialActivity.this);
                View root = inflater.inflate(R.layout.select_polynomial_dialog, null);
                builder.setView(root);

                builder.setTitle("Add polynomial with:");

                final TextView result = root.findViewById(R.id.spd_tv_result);

                RecyclerView recyclerView = root.findViewById(R.id.spd_rv_polynomials);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PolynomialActivity.this);
                recyclerView.setLayoutManager(layoutManager);

                PolynomialAdapter mAdapter = new PolynomialAdapter(list);

                mAdapter.setOnItemClickListener(new PolynomialAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int position, Polynomial pol) {
                        result.setText("Result: " + addition(polynomial, pol).toString());
                    }
                });

                recyclerView.setAdapter(mAdapter);

                final AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setButton(-2, "Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PolynomialActivity.this);
                LayoutInflater inflater = LayoutInflater.from(PolynomialActivity.this);
                View root = inflater.inflate(R.layout.select_polynomial_dialog, null);
                builder.setView(root);

                builder.setTitle("Multiply polynomial with:");

                final TextView result = root.findViewById(R.id.spd_tv_result);

                RecyclerView recyclerView = root.findViewById(R.id.spd_rv_polynomials);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PolynomialActivity.this);
                recyclerView.setLayoutManager(layoutManager);

                PolynomialAdapter mAdapter = new PolynomialAdapter(list);

                mAdapter.setOnItemClickListener(new PolynomialAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int position, Polynomial pol) {

                        if (pol.getGrad() + polynomial.getGrad() > 999) {
                            result.setText("Result too big!");
                        } else {
                            result.setText("Result: " + multiplication(polynomial, pol).toString());
                        }
                    }
                });

                recyclerView.setAdapter(mAdapter);

                final AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setButton(-2, "Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PolynomialActivity.this);
                LayoutInflater inflater = LayoutInflater.from(PolynomialActivity.this);
                View root = inflater.inflate(R.layout.select_polynomial_dialog, null);
                builder.setView(root);

                builder.setTitle("Divide polynomial with:");

                final TextView result = root.findViewById(R.id.spd_tv_result);

                RecyclerView recyclerView = root.findViewById(R.id.spd_rv_polynomials);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PolynomialActivity.this);
                recyclerView.setLayoutManager(layoutManager);

                PolynomialAdapter mAdapter = new PolynomialAdapter(list);

                mAdapter.setOnItemClickListener(new PolynomialAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int position, Polynomial pol) {

                        final Pair<Polynomial, Polynomial> pair;

                        pair = division(polynomial, pol);

                        result.setText("Quotient: " + pair.first.toString() + '\n' + "Remainder: " + pair.second.toString());
                    }
                });

                recyclerView.setAdapter(mAdapter);

                final AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setButton(-2, "Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        valueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PolynomialActivity.this);
                LayoutInflater inflater = LayoutInflater.from(PolynomialActivity.this);
                View root = inflater.inflate(R.layout.value_dialog, null);
                builder.setView(root);

                builder.setTitle("Get the value of the polynomial in:");

                final TextView result = root.findViewById(R.id.value_tv);
                final EditText editValue = root.findViewById(R.id.insert_value);
                Button getValue = root.findViewById(R.id.done_button);

                getValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        result.setText(polynomial.valueIn(Integer.parseInt(editValue.getText().toString())).toString());

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setButton(-2, "Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }

        });

        solutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PolynomialActivity.this);
                LayoutInflater inflater = LayoutInflater.from(PolynomialActivity.this);
                View root = inflater.inflate(R.layout.tv_dialog, null);
                builder.setView(root);

                builder.setTitle("The polynomial's whole solutions are:");

                final TextView result = root.findViewById(R.id.tv);

                ArrayList<Integer> solutions = polynomial.getWholeSolutions();
                StringBuilder g = new StringBuilder();
                for (int i = 0; i < solutions.size(); i++) {
                    g.append(solutions.get(i));
                    if (i < solutions.size() - 1) g.append(", ");
                    else g.append(".");
                }

                if (solutions.isEmpty()) {
                    result.setText("There aren't whole solutions for this polynomial");
                } else {
                    result.setText(g.toString());
                }


                final AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setButton(-2, "Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        derivativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PolynomialActivity.this);
                LayoutInflater inflater = LayoutInflater.from(PolynomialActivity.this);
                View root = inflater.inflate(R.layout.tv_dialog, null);
                builder.setView(root);

                final TextView result = root.findViewById(R.id.tv);

                result.setText(polynomial.getDerivative().toString());

                final AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setButton(-2, "Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        antiderivativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PolynomialActivity.this);
                LayoutInflater inflater = LayoutInflater.from(PolynomialActivity.this);
                View root = inflater.inflate(R.layout.tv_dialog, null);
                builder.setView(root);

                final TextView result = root.findViewById(R.id.tv);

                if (polynomial.getGrad() == 999) {
                    result.setText("Result too big!");
                } else {
                    result.setText(polynomial.getOneAntiderivative(0).toString());
                }


                final AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setButton(-2, "Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        integrateButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PolynomialActivity.this);
                LayoutInflater inflater = LayoutInflater.from(PolynomialActivity.this);
                View root = inflater.inflate(R.layout.integrate_dialog, null);
                builder.setView(root);

                builder.setTitle("Integrate");

                final TextView result = root.findViewById(R.id.value_tv);
                final EditText editFromValue = root.findViewById(R.id.from_button);
                final EditText editToValue = root.findViewById(R.id.to_button);
                Button getValue = root.findViewById(R.id.done_button);

                getValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Integer to, from;

                        to = Integer.parseInt(editToValue.getText().toString());
                        from = Integer.parseInt(editFromValue.getText().toString());

                        result.setText(polynomial.integrate(from, to).toString());

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setButton(-2, "Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PolynomialActivity.this);

                final AlertDialog dialog = builder.create();

                dialog.setTitle("Delete polynomial");
                dialog.setMessage("Are you sure?");

                dialog.setButton(-2, "No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                dialog.setButton(-1, "Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(position);
                        Hawk.put(Constants.POLYNOMIAL_LIST, list);
                        dialog.dismiss();
                        PolynomialActivity.this.finish();
                    }
                });

                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.show();

            }
        });
    }


    private Polynomial addition(Polynomial pol1, Polynomial pol2) {

        Integer[] numarator = new Integer[1000];
        Integer[] numitor = new Integer[1000];
        Integer grad;

        if (pol1.getGrad() > pol2.getGrad()) grad = pol1.getGrad();
        else grad = pol2.getGrad();

        for (int i = grad; i >= 0; i--) {
            if (pol1.getNumarator()[i] == null) {
                pol1.getNumarator()[i] = 0;
                pol1.getNumitor()[i] = 1;
            }

            if (pol2.getNumarator()[i] == null) {
                pol2.getNumarator()[i] = 0;
                pol2.getNumitor()[i] = 1;
            }
        }

        for (int i = 0; i <= grad; i++) {
            numarator[i] = pol1.getNumarator()[i] * pol2.getNumitor()[i] + pol2.getNumarator()[i] * pol1.getNumitor()[i];
            numitor[i] = pol1.getNumitor()[i] * pol2.getNumitor()[i];
        }

        Integer corection = 0;

        for (int i = 0; i <= grad; i++) {
            if (numarator[i] == null) {
                numarator[i] = 0;
                numitor[i] = 1;
            } else {
                if (numarator[i] == 0) numitor[i] = 1;
                else corection = i;
            }
        }

        return new Polynomial(numarator, numitor, corection);
    }

    private Polynomial multiplication(Polynomial pol1, Polynomial pol2) {
        Integer[] numarator = new Integer[1000];
        Integer[] numitor = new Integer[1000];
        Integer grad = pol1.getGrad() + pol2.getGrad();

        for (int i = 0; i <= grad; i++) {
            numarator[i] = 0;
            numitor[i] = 1;
        }

        for (int i = 0; i <= pol1.getGrad(); i++) {
            for (int j = 0; j <= pol2.getGrad(); j++) {
                int addedNuma = pol1.getNumarator()[i] * pol2.getNumarator()[j];
                int addedNumi = pol1.getNumitor()[i] * pol2.getNumitor()[j];

                numarator[i + j] = numarator[i + j] * addedNumi + numitor[i + j] * addedNuma;
                numitor[i + j] = numitor[i + j] * addedNumi;

            }
        }

        return new Polynomial(numarator, numitor, grad);
    }

    private Polynomial multiplyWithConstant(Polynomial pol, int x, int y) {

        Integer[] numa = new Integer[1000];
        Integer[] numi = new Integer[1000];

        for (int i = 0; i <= pol.getGrad(); i++) {
            numa[i] = pol.getNumarator()[i] * x;
            numi[i] = pol.getNumitor()[i] * y;
        }

        return new Polynomial(numa, numi, pol.getGrad());
    }

    private Pair<Polynomial, Polynomial> division(Polynomial pol1, Polynomial pol2) {

        if (pol1.getGrad() < pol2.getGrad()) {

            Integer[] numa = new Integer[1000];
            Integer[] numi = new Integer[1000];
            numa[0] = 0;
            numi[0] = 1;

            Pair<Polynomial, Polynomial> pair = new Pair<>(new Polynomial(numa, numi, 0), pol1);
            return pair;

        } else {

            if (pol1.getGrad() == pol2.getGrad()) {

                Integer[] numa = new Integer[1000];
                Integer[] numi = new Integer[1000];
                numa[0] = pol2.getNumitor()[pol2.getGrad()] * pol1.getNumarator()[pol1.getGrad()];
                numi[0] = pol2.getNumarator()[pol2.getGrad()] * pol1.getNumitor()[pol1.getGrad()];

                if (numa[0] < 0 && numi[0] < 0) {
                    numa[0] *= -1;
                    numi[0] *= -1;
                }

                Pair<Polynomial, Polynomial> pair = new Pair<>(new Polynomial(numa, numi, 0), addition(pol1, multiplyWithConstant(pol2, -1 * numa[0], numi[0])));

                return pair;

            } else {

                Integer[] numaCat = new Integer[1000];
                Integer[] numiCat = new Integer[1000];

                numaCat[pol1.getGrad() - pol2.getGrad()] = pol1.getNumarator()[pol1.getGrad()] * pol2.getNumitor()[pol2.getGrad()];
                numiCat[pol1.getGrad() - pol2.getGrad()] = pol1.getNumitor()[pol1.getGrad()] * pol2.getNumarator()[pol2.getGrad()];

                for (int i = pol1.getGrad() - pol2.getGrad() - 1; i >= 0; i--) {
                    numaCat[i] = 0;
                    numiCat[i] = 1;
                }

                Polynomial catCurent = new Polynomial(numaCat, numiCat, pol1.getGrad() - pol2.getGrad());

                Pair<Polynomial, Polynomial> pair = division(addition(pol1, multiplyWithConstant(multiplication(pol2, catCurent), -1, 1)), pol2);
                Pair<Polynomial, Polynomial> rezultat = new Pair<>(addition(catCurent, pair.first), pair.second);

                return rezultat;
            }

        }

    }


}
