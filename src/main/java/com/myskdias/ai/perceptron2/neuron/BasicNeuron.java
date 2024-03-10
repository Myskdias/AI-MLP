package com.myskdias.ai.perceptron2.neuron;

import com.myskdias.ai.perceptron2.Axone;
import com.myskdias.ai.perceptron2.Function;
import com.myskdias.ai.perceptron2.backtracking.ToolBox;

public class BasicNeuron implements Neuron {

    protected double value = Double.NaN;

    protected double deltaIS = Double.NaN;

    protected Function activationFunction;

    protected Axone[] prevLayer;
    protected Axone[] nextLayer;

    public BasicNeuron(Function activationFunction, Axone[] prevLayer) {
        this.activationFunction = activationFunction;
        this.prevLayer = prevLayer;
    }

    public void setNextLayer(Axone[] layer) {
        this.nextLayer = layer;
    }

    public double getDeltaIS() {
        return deltaIS;
    }

    /**
     * use old or new wij ?
     * @param eta learning rate
     */
    @Override
    public void train(double eta) {

        double[] prevDeltaIS = new double[nextLayer.length];
        double[] prevOmegaILS = new double[nextLayer.length];

        for (int l = 0; l < nextLayer.length; l++) {
            Axone axone = nextLayer[l];
            prevDeltaIS[l] = axone.getNeuron().getDeltaIS();
            prevOmegaILS[l] = axone.getWeight();
        }

        deltaIS = ToolBox.calcDeltaIS(prevDeltaIS, prevOmegaILS, activationFunction, getWeightedSum());

        for (Axone axone : prevLayer) {
            double delta = ToolBox.delta(deltaIS, axone.getNeuron().getValue(), eta);
            axone.removeToWeight(delta);
        }
    }

    /**
     * @return f(z)
     */
    @Override
    public double getValue() {
         if(Double.isNaN(value)) {
             double weightedSum = 0;
             for(Axone axone : prevLayer) {
                 weightedSum += axone.getWeightedValue();
             }
             value = activationFunction.f(weightedSum);
         }
         return value;
    }

    public double getWeightedSum() {
        double weightedSum = 0;
        for(Axone axone : prevLayer) {
            weightedSum += axone.getWeightedValue();
        }
        return weightedSum;
    }

    @Override
    public void clearValue() {
         if(!Double.isNaN(value)) {
             value = Double.NaN;
             deltaIS = Double.NaN;
             for(Axone axone : prevLayer) {
                 axone.getNeuron().clearValue();
             }
         }
    }

}
