package com.polynomialcalculator.alexm.polynomialcalculator;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.polynomialcalculator.alexm.polynomialcalculator.models.Polynomial;

import java.util.ArrayList;

public class PolynomialAdapter extends RecyclerView.Adapter<PolynomialAdapter.MyViewHolder> {
    private ArrayList<Polynomial> mDataset;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView);
        }
    }

    PolynomialAdapter(ArrayList<Polynomial> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public PolynomialAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PolynomialAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_polynomial, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Polynomial polynomial = mDataset.get(position);
        holder.textView.setText(polynomial.toString());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null) {
                    onItemClickListener.onItemClicked(position, polynomial);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(int position, Polynomial polynomial);
    }
}