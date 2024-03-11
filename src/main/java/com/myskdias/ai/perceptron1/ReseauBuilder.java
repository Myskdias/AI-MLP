package com.myskdias.ai.perceptron1;

import java.util.ArrayList;

public class ReseauBuilder {

    private ArrayList<Integer> layersConfig = new ArrayList<>();

    public ReseauBuilder() {

    }

    public ReseauBuilder addLayer(int number) {
        layersConfig.add(number);
        return this;
    }

    public Reseau build() {

        Neurone[] initLayer = new Neurone[0];
        Neurone[] prevLayer = new Neurone[0];

        for(int k = 0; k < layersConfig.size(); k++) {
            int n = layersConfig.get(k);
            Neurone[] layer = new Neurone[n];
            for (int i = 0; i < n; i++) {
                float id =  Float.parseFloat(n+"."+i);
                Arete[] aretes = new Arete[prevLayer.length];
                for(int j = 0; j < prevLayer.length; j++) {
                    Arete arete = new Arete(prevLayer[j], 0.5f);
                    aretes[j] = arete;
                }
                Neurone neurone = new Neurone(aretes, 0.5f);
                layer[i] = neurone;
            }
            prevLayer = layer;
            if(k == 0) {
                initLayer = layer;
            }
        }
        return new Reseau(initLayer,prevLayer);
    }

}
