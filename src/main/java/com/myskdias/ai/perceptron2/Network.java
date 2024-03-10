package com.myskdias.ai.perceptron2;

import com.myskdias.ai.perceptron2.neuron.Neuron;
import org.jetbrains.annotations.NotNull;

public class Network {

    private final Neuron[][] network;

    private ErrorFunction errorFunction;

    public Network(@NotNull Neuron[] @NotNull [] network) {
        this.network = network;
    }

    public double[] calc(double[] values) {
        return null;
    }

    public void train(double[] values, double[] expected) {
        double[] results = calc(values);




    }


}
