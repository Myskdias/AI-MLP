package com.myskdias.ai.perceptron2.neuron;

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

}
