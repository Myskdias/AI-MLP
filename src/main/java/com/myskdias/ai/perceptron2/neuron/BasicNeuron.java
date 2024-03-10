package com.myskdias.ai.perceptron2.neuron;

import com.myskdias.ai.perceptron2.Axon;
import com.myskdias.ai.perceptron2.functions.Function;
import com.myskdias.ai.perceptron2.backtracking.ToolBox;

import java.util.ArrayList;
import java.util.List;

public class BasicNeuron implements Neuron {

    protected double value = Double.NaN;

    protected double deltaIS = Double.NaN;

    protected Function activationFunction;

    protected Axon[] prevLayer;
    protected List<Axon> nextLayer;

    public BasicNeuron(Function activationFunction, Neuron[] prevLayerNeuron) {
        this.activationFunction = activationFunction;
        this.nextLayer = new ArrayList<>();

        prevLayer = new Axon[prevLayerNeuron.length];

        for (int i = 0; i < prevLayerNeuron.length; i++) {
            Axon axon = new Axon(prevLayerNeuron[i]);
            prevLayer[i] = axon;
            prevLayerNeuron[i].addAxon(axon);
        }

    }

    @Override
    public void addAxon(Axon axon) {
        this.nextLayer.add(axon);
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

        double[] prevDeltaIS = new double[nextLayer.size()];
        double[] prevOmegaILS = new double[nextLayer.size()];

        for (int l = 0; l < nextLayer.size(); l++) {
            Axon axon = nextLayer.get(l);
            prevDeltaIS[l] = axon.getNeuron().getDeltaIS();
            prevOmegaILS[l] = axon.getWeight();
        }

        deltaIS = ToolBox.calcDeltaIS(prevDeltaIS, prevOmegaILS, activationFunction, getWeightedSum());

        for (Axon axon : prevLayer) {
            double delta = ToolBox.delta(deltaIS, axon.getNeuron().getValue(), eta);
            axon.removeToWeight(delta);
        }
    }

    /**
     * @return f(z)
     */
    @Override
    public double getValue() {
         if(Double.isNaN(value)) {
             double weightedSum = 0;
             for(Axon axon : prevLayer) {
                 weightedSum += axon.getWeightedValue();
             }
             value = activationFunction.f(weightedSum);
         }
         return value;
    }

    public double getWeightedSum() {
        double weightedSum = 0;
        for(Axon axon : prevLayer) {
            weightedSum += axon.getWeightedValue();
        }
        return weightedSum;
    }

    @Override
    public void clearValue() {
         if(!Double.isNaN(value)) {
             value = Double.NaN;
             deltaIS = Double.NaN;
             for(Axon axon : prevLayer) {
                 axon.getNeuron().clearValue();
             }
         }
    }

}
