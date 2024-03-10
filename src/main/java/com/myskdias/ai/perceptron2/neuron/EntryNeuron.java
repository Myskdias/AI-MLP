package com.myskdias.ai.perceptron2.neuron;

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
