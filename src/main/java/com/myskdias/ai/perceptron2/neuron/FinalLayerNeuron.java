package com.myskdias.ai.perceptron2.neuron;

import com.myskdias.ai.perceptron2.Axone;
import com.myskdias.ai.perceptron2.ErrorFunction;
import com.myskdias.ai.perceptron2.Function;
import com.myskdias.ai.perceptron2.backtracking.ToolBox;

public class FinalLayerNeuron extends BasicNeuron {


    public FinalLayerNeuron(Function activationFunction, Axone[] prevLayer) {
        super(activationFunction, prevLayer);
    }

    public void train(ErrorFunction ef, double[] expected, int i, double eta) {
        deltaIS = ToolBox.calcDeltaISMax(ef,expected, i, activationFunction, getWeightedSum());
        for (Axone axone : prevLayer) {
            double delta = ToolBox.delta(deltaIS, axone.getNeuron().getValue(), eta);
            axone.removeToWeight(delta);
        }
    }

    @Override
    public void train(double eta) {
        throw new RuntimeException("Should not be use, use instead FinalLayerNeuron#train(ErrorFunction ef, double[] expected, int i)");
    }
}
