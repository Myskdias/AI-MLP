package com.myskdias.ai.perceptron2.neuron;

import com.myskdias.ai.perceptron2.Axon;

public class BiasNeuron implements Neuron {

    private Axon[] nextLayer;

    public void setNextLayer(Axon[] layer) {
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

    @Override
    public void addAxon(Axon axon) {

    }

}
