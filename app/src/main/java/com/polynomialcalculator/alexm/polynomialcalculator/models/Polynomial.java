package com.polynomialcalculator.alexm.polynomialcalculator.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Polynomial implements Serializable {
    private ArrayList<Integer> numarator;
    private ArrayList<Integer> numitor;
    private Integer grad;

    public Polynomial(ArrayList<Integer> numarator, ArrayList<Integer> numitor, Integer grad) {
        this.numitor = numitor;
        this.numarator = numarator;
        this.grad = grad;
        simplify();
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

    public Integer getGrad() {
        return grad;
    }

    public void setGrad(Integer grad) {
        this.grad = grad;
    }


    public int toPower(int x, int y) {
        int r = 1;
        for (int i = 1; i <= y; i++) {
            r *= x;
        }
        return r;
    }

    public Double valueIn(int x, int y) {
        Double s = 0.0;
        for (int i = 0; i <= grad; i++) {
            if (numarator.get(i) != 0) {
                s += ((double) numarator.get(i) * (double) toPower(x, i)) / ((double) numitor.get(i) * (double) toPower(y, i));
            }
        }

        return s;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder g = new StringBuilder();
        for (int i = grad; i >= 0; i--) {

            if (numarator.get(i) != 0) {

                if (numarator.get(i) > 0 && i != grad) {
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

    public void simplify() {
        for (int i = 0; i <= grad; i++) {
            int x = numarator.get(i), y = numitor.get(i), n;

            if (x >= y) n = x;
            else n = y;

            for (int j = 2; j * j <= n; j++) {
                while (x % j == 0 && y % j == 0) {
                    x /= j;
                    y /= j;
                }
            }

            numarator.set(i, x);
            numitor.set(i, y);
        }
    }
}
