package com.myskdias.ai.perceptron.neuron;

import com.myskdias.ai.perceptron.Axon;

public class EntryNeuron implements Neuron {

    private double value = Double.NaN;

    public EntryNeuron() {}

    public void eatValue(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void clearValue() {
        this.value = Double.NaN;
    }

    @Override
    public double getWeightedSum() {
        return value;
    }

    /**
     * useless
     */
    @Override
    public void train(double eta) {
    }

    /**
     * useless
     * @return 0
     */
    @Override
    public double getDeltaIS() {
        return 0;
    }

    /**
     * do nothing because it s useless for an entry neuron to know it s successor
     * @param axon axon
     */
    @Override
    public void addAxon(Axon axon) {

    }
}
