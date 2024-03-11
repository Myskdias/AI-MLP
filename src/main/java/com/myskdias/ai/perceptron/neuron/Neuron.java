package com.myskdias.ai.perceptron.neuron;

import com.myskdias.ai.perceptron.Axon;

public interface Neuron {

    /**
     *
     * @return f(z)
     */
    public double getValue();

    public void clearValue();

    /**
     *
     * @return z
     */
    public double getWeightedSum();

    /**
     *
     * @param eta learning rate
     */
    public void train(double eta);

    double getDeltaIS();

    public void addAxon(Axon axon);

}
