package com.myskdias.ai.perceptron.neuron;

import com.myskdias.ai.perceptron.Axon;

import java.util.ArrayList;
import java.util.List;

public class BiasNeuron implements Neuron {

    private final List<Axon> nextLayer = new ArrayList<>();

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

    /**
     * do nothing because it s useless for an entry neuron to know it s successor
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
