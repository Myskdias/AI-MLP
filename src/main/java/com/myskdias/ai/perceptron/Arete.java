package com.myskdias.ai.perceptron;

public class Arete {

    private final Neurone n;
    private float w;

    public Arete(Neurone n, float w) {
        this.n = n;
        this.w = w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public Neurone getN() {
        return n;
    }

    public float getW() {
        return w;
    }

    public float getValue() {
        return getW()*getN().getValue();
    }


}
