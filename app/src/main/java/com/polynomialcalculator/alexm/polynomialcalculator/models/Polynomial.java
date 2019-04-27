package com.polynomialcalculator.alexm.polynomialcalculator.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Polynomial implements Serializable {
    private Integer[] numarator;
    private Integer[] numitor;
    private Integer grad;

    public Polynomial(Integer[] numarator, Integer[] numitor, Integer grad) {
        this.numitor = numitor;
        this.numarator = numarator;
        this.grad = grad;
        simplify();
    }

    public Integer[] getNumarator() {
        return numarator;
    }

    public void setNumarator(Integer[] numarator) {
        this.numarator = numarator;
    }

    public Integer[] getNumitor() {
        return numitor;
    }

    public void setNumitor(Integer[] numitor) {
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
            if (numarator[i] != 0) {
                s += ((double) numarator[i] * (double) toPower(x, i)) / ((double) numitor[i] * (double) toPower(y, i));
            }
        }

        return s;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder g = new StringBuilder();
        for (int i = grad; i >= 0; i--) {

            if (numarator[i] != 0) {

                if (numarator[i] > 0 && i != grad) {
                    g.append(" + ");
                }

                if (numitor[i] != 1) {
                    g.append(numarator[i]).append("/").append(numitor[i]).append(" X^").append(i);
                } else {
                    g.append(numarator[i]).append(" X^").append(i);
                }

            }
        }
        return g.toString();
    }

    public void simplify() {
        for (int i = 0; i <= grad; i++) {
            int x = numarator[i], y = numitor[i], n;

            if (x >= y) n = x;
            else n = y;

            for (int j = 2; j * j <= n; j++) {
                while (x % j == 0 && y % j == 0) {
                    x /= j;
                    y /= j;
                }
            }

            numarator[i] = x;
            numitor[i] =  y;
        }
    }
}
