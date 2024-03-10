package com.myskdias.ai.perceptron;

public class Neurone {

    private float bias = 0f;

    private final float id;

    private final Arete[] in;

    private float value = Float.NaN;

    public Neurone(Arete[] in, float id) {
        this.in = in;
        this.id = id;
    }

    public float getId() {
        return id;
    }

    public Arete[] getIn() {
        return in;
    }

    private float calcValue() {
        float result = bias;
        for(Arete a : in) {
            result += a.getValue();
        }
        return sigmo(result);
    }

    public float getValue() {
        if(Float.isNaN(value)) {
            value = calcValue();
        }
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void clearValue() {
        if(!Float.isNaN(value)) {
            this.value = Float.NaN;
            for(Arete arete : in) {
                arete.getN().clearValue();
            }
        }
    }

    public static float sigmo(float u) {
        return (float) (1 / (1 + Math.exp(-u)));
    }


}
