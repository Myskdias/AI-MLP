package com.myskdias.ai.perceptron;

import com.myskdias.ai.perceptron.functions.ErrorFunction;
import com.myskdias.ai.perceptron.neuron.BasicNeuron;
import com.myskdias.ai.perceptron.neuron.EntryNeuron;
import com.myskdias.ai.perceptron.neuron.FinalLayerNeuron;
import com.myskdias.ai.perceptron.neuron.Neuron;
import com.myskdias.ai.perceptron.parsing.Unit;
import com.myskdias.ai.perceptron.parsing.Parser;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Network {

    private final Neuron[][] network;

    /**
     * Don't use this constructor. You should rather use the {@link NetworkBuilder} class.
     * @param network a network represented by a 2D-Array
     */
    public Network(@NotNull Neuron[] @NotNull [] network) {
        this.network = network;
    }

    /**
     * clear value of each neuron., i.e. the extent to which they were activated with previous entry.
     * </br> you don't need to call this method before the {@link #calc(double[] values)} method.
     */
    public void clearValues() {
        for(Neuron n : network[network.length-1]) {
            n.clearValue();
        }
    }

    /**
     *
     * @return the value of each neuron of the last layer of the network
     */
    public double[] getResults() {
        double[] results = new double[network[network.length - 1].length];

        for (int i = 0; i < network[network.length - 1].length; i++) {
            Neuron n = network[network.length - 1][i];
            results[i] = n.getValue();
        }

        return results;
    }

    /**
     * Note that the bias neuron is always located on the last spot of each row
     * @return the neurons composing the network. Each row represents a layer of the network.
     *
     */
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

    /**
     *
     * Apply one iteration of the train algorithm with the given data. </br>
     * In the future, I plan to create a method that takes data, a number of epoch and an eta as input which will call
     * this method several time.
     *
     * @param values input values
     * @param expected expected output values
     * @param eta learning rate
     * @param errorFunction self-explanatory
     */
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

    public void save(@NotNull File file) {
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            PrintWriter writer = new PrintWriter(file);
            StringBuilder builder = new StringBuilder();
            builder.append("{{");
            for(Neuron[] neurons : network) {
                builder.append(neurons.length).append(", ");
            }
            builder.delete(builder.length()-2, builder.length());
            builder.append("}, ");

            builder.append("{");
            for (int i = 0; i < network.length - 1; i++) {
                Neuron[] neurons = network[i];
                builder.append("{");
                for(Neuron n : neurons) {
                    builder.append("{");
                    for(Axon axon : n.getAxons()) {
                        builder.append(axon.getWeight()).append(", ");
                    }
                    builder.delete(builder.length()-2, builder.length());
                    builder.append("}, ");
                }
                builder.delete(builder.length()-2, builder.length());
                builder.append("}, ");
            }
            builder.delete(builder.length()-2, builder.length());
            builder.append("}, ");
            builder.append("{");
            builder.append(((FinalLayerNeuron)network[network.length-1][0]).getActivationFunction().getClass().getCanonicalName());
            builder.append("}, ");
            builder.append("{");
            if(network.length > 2) {
                builder.append(((BasicNeuron)network[network.length-2][0]).getActivationFunction().getClass().getCanonicalName());
            }
            builder.append("}}");
            writer.print(builder.toString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * @param file a file that end with .af
     * @return the network serialized in this file
     */
    public static Network load(@NotNull File file) throws FileNotFoundException {
        if(!file.exists()) {
            throw new FileNotFoundException();
        }

        Parser parser = new Parser(file);
        Unit unit = parser.nextUnit();
        return null;
    }


}
