package com.myskdias.ai.perceptron.neuron;

import com.myskdias.ai.perceptron.Axon;

import java.util.ArrayList;
import java.util.List;

public class EntryNeuron implements Neuron {

    private double value = Double.NaN;

    protected List<Axon> nextLayer = new ArrayList<>();

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
     * @param axon axon
     */
    @Override
    public void addAxon(Axon axon) {
        nextLayer.add(axon);
    }

    /**
     * @return the axons linking to the next layer
     */
    @Override
    public List<Axon> getAxons() {
        return nextLayer;
    }
}
