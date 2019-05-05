package com.polynomialcalculator.alexm.polynomialcalculator.models;

import android.support.annotation.NonNull;

import javax.xml.transform.dom.DOMLocator;
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
        fixGrad();
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

    public void fixGrad(){
        for(int i = grad; i >= 0; i--){
            if(numarator[i] != 0){
                this.grad = i;
                return;
            }
        }
        this.grad = 0;
    }

    public int toPower(int x, int y) {
        int r = 1;
        for (int i = 1; i <= y; i++) {
            r *= x;
        }
        return r;
    }

    public Integer numitorComun() {
        Integer x = 1;

        for(int i = 0; i <= grad; i++) {
            if(numitor[i] != 1 && numitor[i] != 0) {

                Integer copieCmmmc = x, copieEL = numitor[i];

                while(copieCmmmc != copieEL){
                    if (copieCmmmc > copieEL)
                        copieCmmmc = copieCmmmc - copieEL;
                    else if (copieEL > copieCmmmc)
                        copieEL = copieEL - copieCmmmc;
                }

                x = x*(numitor[i]/copieEL);
            }
        }

        return x;
    }

    public Double valueIn(int x) {
        Double s = 0.0;
        Double numComun = (double) numitorComun();

        for (int i = 0; i <= grad; i++) {
            if (numarator[i] != 0) {
                s += ((double) numarator[i] * (double) toPower(x, i)) * (numComun / (double) numitor[i]);
            }
        }

        return s / numComun;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder g = new StringBuilder();
        for (int i = grad; i >= 0; i--) {

            if (numarator[i] != 0) {

                if (numarator[i] > 0 && i != grad) {
                    g.append("+ ");
                }

                if (numitor[i] != 1) {
                    g.append(numarator[i]).append("/").append(numitor[i]).append(" X^").append(i);
                } else {
                    g.append(numarator[i]).append(" X^").append(i);
                }
                g.append(" ");

            }
        }
        return g.toString();
    }

    public void simplify() {
        for (int i = 0; i <= grad; i++) {
            int x = numarator[i], y = numitor[i], n;

            if(x < 0 && y < 0){
                x *= -1;
                y *= -1;
            }

            if(x > 0 && y < 0){
                x *= -1;
                y *= -1;
            }

            if (x >= y) n = x;
            else n = y;

            for (int j = 2; j <= n; j++) {
                while (x % j == 0 && y % j == 0) {
                    x /= j;
                    y /= j;
                }
            }

            this.numarator[i] = x;
            this.numitor[i] =  y;
        }
    }

    public Polynomial getDerivative() {
        Integer[] newNumarator = new Integer[1000];
        Integer[] newNumitor = new Integer[1000];

        if(grad == 0) {

            newNumarator[0] = 0;
            newNumitor[0] = 1;
            return new Polynomial(newNumarator, newNumitor, 0);

        } else {

            for(int i = 1; i <= grad; i++) {
                newNumarator[i-1] = i * numarator[i];
                newNumitor[i-1] = numitor[i];
            }
            return new Polynomial(newNumarator, newNumitor, grad - 1);

        }
    }

    public Polynomial getOneAntiderivative(int constant) {
        Integer[] newNumarator = new Integer[1000];
        Integer[] newNumitor = new Integer[1000];

        newNumarator[0] = constant;
        newNumitor[0] = 1;

        for (int i = 0; i <= grad; i++) {
            newNumarator[i+1] = numarator[i];
            newNumitor[i+1] = numitor[i] * (i+1);
        }

        return new Polynomial(newNumarator, newNumitor, grad + 1);
    }

    public Double integrate(int a, int b) {
        Polynomial antiderivative = getOneAntiderivative(22);
        return antiderivative.valueIn(b) - antiderivative.valueIn(a);
    }

    public ArrayList<Integer> getWholeSolutions(){
        ArrayList<Integer> solutions = new ArrayList<>();
        Integer t;

        if(numarator[0] == 0) {
            solutions.add(0);

            int k = 1;
            while(numarator[k] == 0) k++;
            t = numarator[k];

        } else {
            t = numarator[0];
        }

        if(t < 0) t *= -1;

        for(int i = 1; i <= t; i++) {
            if(t % i == 0){
                if(valueIn(i) == 0) solutions.add(i);
                if(valueIn(-i) == 0) solutions.add(-i);
            }
        }

        return solutions;
    }

}
