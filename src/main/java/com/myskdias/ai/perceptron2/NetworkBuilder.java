package com.myskdias.ai.perceptron2;

import com.myskdias.ai.perceptron2.functions.Function;
import com.myskdias.ai.perceptron2.neuron.*;

import java.util.ArrayList;

public class NetworkBuilder {

    private final ArrayList<Integer> layerConfig = new ArrayList<>();

    private final int firstLayerSize;
    private final int lastLayerSize;

    private final Function hiddenLayerAF;

    private final Function finalLayerAF;

    public NetworkBuilder(int firstLayerSize, int lastLayerSize, Function hiddenLayerAF, Function finalLayerAF) {
        this.firstLayerSize = firstLayerSize;
        this.lastLayerSize = lastLayerSize;
        this.hiddenLayerAF = hiddenLayerAF;
        this.finalLayerAF = finalLayerAF;
    }

    public NetworkBuilder addLayer(int numberOfNeuron) {
        layerConfig.add(numberOfNeuron);
        return this;
    }



    public Network build() {

        Neuron[][] network = new Neuron[layerConfig.size() + 2][];

        Neuron[] firstLayer = new Neuron[firstLayerSize + 1];
        for (int i = 0; i < firstLayerSize; i++) {
            firstLayer[i] = new EntryNeuron();
        }
        firstLayer[firstLayerSize] = new BiasNeuron();
        network[0] = firstLayer;

        Neuron[] prevLayer = firstLayer;

        for (int s = 0; s < layerConfig.size(); s++) {

            int size = layerConfig.get(s);

            Neuron[] hiddenLayer = new Neuron[size+1];

            for (int i = 0; i < size; i++) {
                hiddenLayer[i] = new BasicNeuron(hiddenLayerAF, prevLayer);
            }

            hiddenLayer[size] = new BiasNeuron();
            network[s] = hiddenLayer;
            prevLayer = hiddenLayer;

        }

        FinalLayerNeuron[] lastLayer = new FinalLayerNeuron[lastLayerSize];
        for (int i = 0; i < lastLayerSize; i++) {
            lastLayer[i] = new FinalLayerNeuron(finalLayerAF, prevLayer);
        }

        network[layerConfig.size()+1] = lastLayer;

        return new Network(network);
    }

}
