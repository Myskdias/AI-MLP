package com.myskdias.ai.perceptron;

import com.myskdias.ai.perceptron.neuron.Neuron;

import java.util.Random;

public class Axon {

    private final Neuron neuron;

    private double weight;

    public Axon(Neuron neuron) {
        this.neuron = neuron;
        this.weight = new Random().nextDouble();
    }

    public Axon(Neuron neuron, double weight) {
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
        if(delta != delta) {
            throw new RuntimeException("oere");
        }
        weight -= delta;
    }

    @Override
    public String toString() {
        return "weight: "+weight;
    }
}
