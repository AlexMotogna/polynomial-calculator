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
    private ArrayList<Pair<Integer, Integer>> integers;

    Polynomial polynomial;

    // ingegers.get(index).first = numarator; ingegers.get(index).second = numitor;

    private Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new CreatePolynomialAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.create_polynomial_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder vh, int position) {
        final Pair<Integer, Integer> coef = integers.get(position);

        vh.numarator.setText(coef.first.toString());
        vh.numitor.setText(coef.second.toString());
        vh.putere.setText(coef.second.toString());

        if (position == integers.size() - 1) {
            vh.input_numarator.setVisibility(View.VISIBLE);
            vh.input_numitor.setVisibility(View.VISIBLE);
            vh.input_putere.setVisibility(View.VISIBLE);
            vh.addCoefficients.setVisibility(View.VISIBLE);
        } else {
            vh.input_numarator.setVisibility(View.GONE);
            vh.input_numitor.setVisibility(View.GONE);
            vh.input_putere.setVisibility(View.GONE);
            vh.addCoefficients.setVisibility(View.GONE);
        }

        vh.addCoefficients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String putere = vh.input_putere.getText().toString();
                String numitor = vh.input_numitor.getText().toString();
                String numarator = vh.input_numarator.getText().toString();

                if(putere.isEmpty() && numitor.isEmpty() && numarator.isEmpty() || numitor.equals("0")) {
                    Toast.makeText(context, "Wrong data! Try again.", Toast.LENGTH_SHORT).show();
                } else {
                    // logic to create polynomial
                    // add value
                    notifyDataSetChanged();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return integers.size();
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

    CreatePolynomialAdapter(Context context, ArrayList<Pair<Integer, Integer>> integers) {
        this.integers = integers;
        this.context = context;
    }
}
