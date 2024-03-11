package com.myskdias.ai.perceptron;

import com.myskdias.ai.perceptron.functions.ErrorFunction;
import com.myskdias.ai.perceptron.neuron.EntryNeuron;
import com.myskdias.ai.perceptron.neuron.FinalLayerNeuron;
import com.myskdias.ai.perceptron.neuron.Neuron;
import org.jetbrains.annotations.NotNull;

public class Network {

    private final Neuron[][] network;

    public Network(@NotNull Neuron[] @NotNull [] network) {
        this.network = network;
    }

    public void clearValues() {
        for(Neuron n : network[network.length-1]) {
            n.clearValue();
        }
    }

    public double[] getResults() {
        double[] results = new double[network[network.length - 1].length];

        for (int i = 0; i < network[network.length - 1].length; i++) {
            Neuron n = network[network.length - 1][i];
            results[i] = n.getValue();
        }

        return results;
    }

    public Neuron[][] getNetwork() {
        return network;
    }

    /**
     *
     * @param values the entry values
     * @return the values computed by the network
     */
    public double[] calc(double[] values) {
        clearValues();
        //we do not look at the last neuron because it is the bias neuron
        for (int i = 0; i < network[0].length - 1; i++) {

            EntryNeuron en = (EntryNeuron) network[0][i];
            en.eatValue(values[i]);
        }

        return getResults();
    }

    public void train(double[] values, double[] expected, double eta, ErrorFunction errorFunction) {
        calc(values);

        for (int s = network.length - 1; s > 0; s--) {
            if(s == network.length - 1) {
                for (int i = 0; i < network[s].length; i++) {
                    FinalLayerNeuron neuron = (FinalLayerNeuron) network[s][i];
                    neuron.train(errorFunction, expected, i, eta);
                }
            } else {

                for (int i = 0; i < network[s].length - 1; i++) {
                    network[s][i].train(eta);
                }
            }
        }


    }


}
