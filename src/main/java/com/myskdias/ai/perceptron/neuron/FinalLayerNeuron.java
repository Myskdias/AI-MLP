package com.myskdias.ai.perceptron.neuron;

import com.myskdias.ai.perceptron.Axon;
import com.myskdias.ai.perceptron.functions.ErrorFunction;
import com.myskdias.ai.perceptron.functions.Function;

public class FinalLayerNeuron extends BasicNeuron {


    public FinalLayerNeuron(Function activationFunction, Neuron[] prevLayer) {
        super(activationFunction, prevLayer);
    }

    public void train(ErrorFunction ef, double[] expected, int i, double eta) {
        double sum = getWeightedSum();
        //cal deltaIS
        deltaIS = ef.getPartialDerivative(expected, i).f(activationFunction.f(sum))
                * activationFunction.fp(sum);
        for (Axon axon : prevLayer) {
            double delta = deltaIS * axon.getNeuron().getValue()*eta;
            axon.removeToWeight(delta);
        }
    }

    @Override
    public void train(double eta) {
        throw new RuntimeException("Should not be used, use instead FinalLayerNeuron#train(ErrorFunction ef, double[] expected, int i)");
    }
}
