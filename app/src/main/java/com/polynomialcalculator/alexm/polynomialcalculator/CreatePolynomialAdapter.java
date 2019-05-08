package com.polynomialcalculator.alexm.polynomialcalculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.polynomialcalculator.alexm.polynomialcalculator.models.Polynomial;

import java.util.ArrayList;

public class CreatePolynomialAdapter extends RecyclerView.Adapter<CreatePolynomialAdapter.MyViewHolder> {
    Integer[] numarator = new Integer[1000];
    Integer[] numitor = new Integer[1000];
    ArrayList<Integer> puteri = new ArrayList<>();
    Integer grad = 0;
    Polynomial polynomial;

    private Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new CreatePolynomialAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.create_polynomial_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder vh, final int position) {
        if(position == 0) {

            vh.input_numarator.setVisibility(View.VISIBLE);
            vh.input_numitor.setVisibility(View.VISIBLE);
            vh.input_putere.setVisibility(View.VISIBLE);
            vh.addCoefficients.setVisibility(View.VISIBLE);

            try{
                Integer aux = puteri.get(position);
                vh.input_numarator.setVisibility(View.GONE);
                vh.input_numitor.setVisibility(View.GONE);
                vh.input_putere.setVisibility(View.GONE);
                vh.addCoefficients.setVisibility(View.GONE);
            } catch (Exception e) {

            }

        } else {
            Integer putere = puteri.get(position - 1);

            final Pair<Integer, Integer> coef = new Pair<>(numarator[putere], numitor[putere]);

            vh.numarator.setText(coef.first.toString());
            vh.numitor.setText(coef.second.toString());
            vh.putere.setText(putere.toString());

            try {
                Integer aux = puteri.get(position);

            } catch (Exception e) {
                vh.input_numarator.setVisibility(View.VISIBLE);
                vh.input_numitor.setVisibility(View.VISIBLE);
                vh.input_putere.setVisibility(View.VISIBLE);
                vh.addCoefficients.setVisibility(View.VISIBLE);
            }

        }

        vh.addCoefficients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String auxPutere = vh.input_putere.getText().toString();
                String auxNumarator = vh.input_numarator.getText().toString();
                String auxNumitor = vh.input_numitor.getText().toString();

                if(auxPutere.isEmpty() || auxNumitor.isEmpty() || auxNumarator.isEmpty() || auxNumitor.equals("0")  || Integer.parseInt(auxPutere) > 999 || checkIfExists(Integer.parseInt(auxPutere))) {
                    Toast.makeText(context, "Wrong data! Try again.", Toast.LENGTH_SHORT).show();
                } else {

                    vh.input_numarator.setVisibility(View.GONE);
                    vh.input_numitor.setVisibility(View.GONE);
                    vh.input_putere.setVisibility(View.GONE);
                    vh.addCoefficients.setVisibility(View.GONE);


                    Integer putere = Integer.parseInt(auxPutere);
                    Integer numa = Integer.parseInt(auxNumarator);
                    Integer numi = Integer.parseInt(auxNumitor);

                    numarator[putere] = numa;
                    numitor[putere] = numi;

                    if(putere > grad){
                        grad = putere;
                    }

                    puteri.add(putere);

                    notifyDataSetChanged();

                    ((CreatePolynomialActivity)context).updateTv();
                }
            }
        });
    }

    private boolean checkIfExists(int x){
        for(int i = 0; i < puteri.size(); i++){
            if(puteri.get(i) == x) return true;
        }

        return false;
    }


    public Polynomial getFinalPolynomial(){

        for (int i = grad; i >= 0; i--) {
            if(numarator[i] == null) {
                numarator[i] = 0;
                numitor[i] = 1;
            }
        }

        polynomial = new Polynomial(numarator, numitor, grad);

        return polynomial;
    }


    @Override
    public int getItemCount() {
        return puteri.size() + 1;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        EditText input_putere;
        EditText input_numarator;
        EditText input_numitor;
        TextView putere;
        TextView numarator;
        TextView numitor;
        Button addCoefficients;

        MyViewHolder(View v) {
            super(v);
            input_putere = v.findViewById(R.id.input_putere);
            input_numarator = v.findViewById(R.id.input_numarator);
            input_numitor = v.findViewById(R.id.input_numitor);
            putere = v.findViewById(R.id.putere);
            numarator = v.findViewById(R.id.numarator);
            numitor = v.findViewById(R.id.numitor);
            addCoefficients = v.findViewById(R.id.add_coefficients);
        }
    }

    CreatePolynomialAdapter(Context context, Integer[] numa, Integer[] numi, ArrayList<Integer> puteri) {
        this.numarator = numa;
        this.numitor = numi;
        this.context = context;
        this.puteri = puteri;
    }
}
