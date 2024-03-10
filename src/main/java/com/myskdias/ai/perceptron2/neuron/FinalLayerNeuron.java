package com.myskdias.ai.perceptron2.neuron;

import com.myskdias.ai.perceptron2.Axon;
import com.myskdias.ai.perceptron2.functions.ErrorFunction;
import com.myskdias.ai.perceptron2.functions.Function;
import com.myskdias.ai.perceptron2.backtracking.ToolBox;

public class FinalLayerNeuron extends BasicNeuron {


    public FinalLayerNeuron(Function activationFunction, Neuron[] prevLayer) {
        super(activationFunction, prevLayer);
    }

    public void train(ErrorFunction ef, double[] expected, int i, double eta) {
        deltaIS = ToolBox.calcDeltaISMax(ef,expected, i, activationFunction, getWeightedSum());
        for (Axon axon : prevLayer) {
            double delta = ToolBox.delta(deltaIS, axon.getNeuron().getValue(), eta);
            axon.removeToWeight(delta);
        }
    }

    @Override
    public void train(double eta) {
        throw new RuntimeException("Should not be used, use instead FinalLayerNeuron#train(ErrorFunction ef, double[] expected, int i)");
    }
}
