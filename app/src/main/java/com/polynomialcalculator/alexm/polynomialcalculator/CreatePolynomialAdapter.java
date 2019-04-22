package com.polynomialcalculator.alexm.polynomialcalculator;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CreatePolynomialAdapter extends RecyclerView.Adapter<CreatePolynomialAdapter.MyViewHolder> {
    private ArrayList<Pair<Integer, Integer>> integers;
    // ingegers.get(index).first = numarator; ingegers.get(index).second = numitor;

    private boolean isLastNumaratorEmpty;
    private boolean isLastNumitorEmpty;

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

        if (position == integers.size() - 1) {
            vh.input_numarator.setVisibility(View.VISIBLE);
            vh.input_numitor.setVisibility(View.VISIBLE);
        } else {
            vh.input_numarator.setVisibility(View.GONE);
            vh.input_numitor.setVisibility(View.GONE);
        }

        vh.input_numarator.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isLastNumaratorEmpty = vh.input_numarator.getText().toString().isEmpty();
            }
        });

        vh.input_numitor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isLastNumitorEmpty = vh.input_numitor.getText().toString().isEmpty();
            }
        });
    }

    @Override
    public int getItemCount() {
        return integers.size();
    }

    public boolean isLastEmpty() {
        return isLastNumaratorEmpty && isLastNumitorEmpty;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        EditText input_numarator;
        EditText input_numitor;
        TextView numarator;
        TextView numitor;

        MyViewHolder(View v) {
            super(v);
            input_numarator = v.findViewById(R.id.input_numarator);
            input_numitor = v.findViewById(R.id.input_numitor);
            numarator = v.findViewById(R.id.numarator);
            numitor = v.findViewById(R.id.numitor);
        }
    }

    CreatePolynomialAdapter(ArrayList<Pair<Integer, Integer>> integers) {
        this.integers = integers;
    }
}
