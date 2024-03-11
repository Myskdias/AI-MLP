package com.myskdias.ai.perceptron1;

public class Reseau {

    private final Neurone[] finalLayer;

    private final Neurone[] initialLayer;

    public Reseau(Neurone[] initialLayer, Neurone[] finalLayer) {
        this.finalLayer = finalLayer;
        this.initialLayer = initialLayer;
    }

    public float[] calc(float ... values ) {
        if(values.length != initialLayer.length) {
            System.out.println("Error, not the right number of value("+values.length+")"+" while IL has "+initialLayer.length+" values");
            return new float[0];
        }

        for(Neurone neurone : finalLayer) {
            neurone.clearValue();
        }

        for (int i = 0; i < values.length; i++) {
            initialLayer[i].setValue(values[i]);
        }

        float[] results = new float[finalLayer.length];

        for (int i = 0; i < finalLayer.length; i++) {
            results[i] = finalLayer[i].getValue();
        }

        return results;
    }

    public Neurone[] getFinalLayer() {
        return finalLayer;
    }

    public Neurone[] getInitialLayer() {
        return initialLayer;
    }
}
