package com.myskdias.ai.perceptron2.neuron;

import com.myskdias.ai.perceptron2.Axone;

public class BiasNeuron implements Neuron {

    private Axone[] nextLayer;

    public void setNextLayer(Axone[] layer) {
        this.nextLayer = layer;
    }

    @Override
    public double getValue() {
        return 1;
    }

    @Override
    public void clearValue() { }

    @Override
    public double getWeightedSum() {
        return 1;
    }

    /**
     * useless
     */
    @Override
    public void train(double eta) { }

    /**
     * useless
     * @return 0
     */
    @Override
    public double getDeltaIS() {
        return 0;
    }

}
