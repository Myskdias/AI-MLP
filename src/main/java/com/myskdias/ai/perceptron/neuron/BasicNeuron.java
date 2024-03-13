package com.myskdias.ai.perceptron.neuron;

import com.myskdias.ai.perceptron.Axon;
import com.myskdias.ai.perceptron.functions.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicNeuron implements Neuron {

    private int layer;

    private int number;

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
            prevLayerNeuron[i].addAxon(new Axon(this));
        }

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }

    public Axon[] getPrevLayer() {
        return prevLayer;
    }

    @Override
    public void addAxon(Axon axon) {
        this.nextLayer.add(axon);
    }

    /**
     * @return the axons linking to the next layer
     */
    @Override
    public List<Axon> getAxons() {
        return nextLayer;
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

        double[] prevDeltaIs = new double[nextLayer.size()];
        double[] prevOmegaILs = new double[nextLayer.size()];

        for (int l = 0; l < nextLayer.size(); l++) {
            Axon axon = nextLayer.get(l);
            prevDeltaIs[l] = axon.getNeuron().getDeltaIS();
            prevOmegaILs[l] = axon.getWeight();
        }

        //Calculating delta_i^s
        double zis = getWeightedSum();

        //deltaIS = ToolBox.calcDeltaIS(prevDeltaIS, prevOmegaILS, activationFunction, getWeightedSum());
        int N = prevDeltaIs.length;

        double sum = 0;
        for (int l = 0; l < N; l++) {
            sum += prevDeltaIs[l] * prevOmegaILs[l];
        }

        deltaIS =  sum * activationFunction.fp(zis);


        if(deltaIS != deltaIS) {
            throw new RuntimeException("deltais"+ Arrays.toString(prevDeltaIs)+" "+
                    ((BasicNeuron)nextLayer.get(0).getNeuron()).getLayer()
                    + " "+ ((BasicNeuron)nextLayer.get(0).getNeuron()).getNumber()
                    +" "+getLayer()+" "+getNumber());
        }

        for (Axon axon : prevLayer) {
            double delta = deltaIS*axon.getNeuron().getValue()*eta;
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

    public Function getActivationFunction() {
        return activationFunction;
    }
}
