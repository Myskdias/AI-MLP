package com.myskdias.ai.perceptron2;

import com.myskdias.ai.perceptron2.neuron.Neuron;

public class Axone {

    private Neuron neuron;

    private double weight;

    public Axone(Neuron neuron) {
        this.neuron = neuron;
        this.weight = 0.5;
    }

    public Axone(Neuron neuron, double weight) {
        this.neuron = neuron;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public Neuron getNeuron() {
        return neuron;
    }

    public double getWeightedValue() {
        return weight * neuron.getValue();
    }

    public void removeToWeight(double delta) {
        weight -= delta;
    }

}
