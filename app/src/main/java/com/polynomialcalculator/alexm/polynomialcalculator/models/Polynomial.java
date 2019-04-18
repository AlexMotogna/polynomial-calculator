package com.polynomialcalculator.alexm.polynomialcalculator.models;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Integer> numarator;
    private ArrayList<Integer> numitor;
    private int grad;

    public Polynomial(ArrayList<Integer> numarator, ArrayList<Integer> numitor, int grad) {
        this.numitor = numitor;
        this.numarator = numarator;
        this.grad = grad;
    }

    public ArrayList<Integer> getNumarator() {
        return numarator;
    }

    public void setNumarator(ArrayList<Integer> numarator) {
        this.numarator = numarator;
    }

    public ArrayList<Integer> getNumitor() {
        return numitor;
    }

    public void setNumitor(ArrayList<Integer> numitor) {
        this.numitor = numitor;
    }

    public int getGrad() {
        return grad;
    }

    public void setGrad(int grad) {
        this.grad = grad;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder g = new StringBuilder();
        for (int i = grad; i >= 0; i--) {
            if (numarator.get(i) != 0) {
                if(numarator.get(i) > 0 && i != grad) {
                    g.append(" + ");
                }
                if (numitor.get(i) != 1) {
                    g.append(numarator.get(i)).append("/").append(numitor.get(i)).append(" X^").append(i);
                } else {
                    g.append(numarator.get(i)).append(" X^").append(i);
                }
            }
        }
        return g.toString();
    }
}
