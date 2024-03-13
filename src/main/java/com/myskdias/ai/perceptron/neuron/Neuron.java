package com.myskdias.ai.perceptron.neuron;

import com.myskdias.ai.perceptron.Axon;

import java.util.List;

public interface Neuron {

    /**
     *
     * @return f(z)
     */
    public double getValue();

    /**
     *  clear the value of the neurons.</br>
     *  This value correspond to the degree of activation of the neuron
     */
    public void clearValue();

    /**
     * @return z
     */
    public double getWeightedSum();

    /**
     *
     * @param eta learning rate
     */
    public void train(double eta);

    /**
     * @return essential value to calculate the dE/dw of an axon linked the prev layer to this layer
     */
    double getDeltaIS();

    /**
     * @param axon an axon linking to the next layer
     */
    public void addAxon(Axon axon);

    /**
     * @return the axons linking to the next layer
     */
    public List<Axon> getAxons();

}
