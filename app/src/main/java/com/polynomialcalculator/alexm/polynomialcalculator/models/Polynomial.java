package com.polynomialcalculator.alexm.polynomialcalculator.models;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Float> numarator;
    private ArrayList<Integer> numitor;

    public Polynomial(ArrayList<Integer> numarator, ArrayList<Integer> numitor) {

    }

    public ArrayList<Float> getNumarator() {
        return numarator;
    }

    public void setNumarator(ArrayList<Float> numarator) {
        this.numarator = numarator;
    }

    public ArrayList<Integer> getNumitor() {
        return numitor;
    }

    public void setNumitor(ArrayList<Integer> numitor) {
        this.numitor = numitor;
    }

    @NonNull
    @Override
    public String toString() {
//        for ();
        return "";
    }
}
