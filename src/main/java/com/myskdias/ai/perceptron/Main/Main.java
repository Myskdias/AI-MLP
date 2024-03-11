package com.myskdias.ai.perceptron.Main;

import com.myskdias.ai.perceptron.Network;
import com.myskdias.ai.perceptron.NetworkBuilder;
import com.myskdias.ai.perceptron.functions.ArctanFunction;
import com.myskdias.ai.perceptron.functions.ErrorFunction;
import com.myskdias.ai.perceptron.functions.SigmoFunction;
import com.myskdias.ai.perceptron.functions.SquareErrorFunction;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        NetworkBuilder networkBuilder = new NetworkBuilder(2, 2, new ArctanFunction(), new SigmoFunction())
                .addLayer(3);

        Network network = networkBuilder.build();

        double eta = 0.001;
        ErrorFunction ef = new SquareErrorFunction();

        double[][] features = new double[][] {

                new double[] {0, 0},
                new double[] {0, 1},
                new double[] {1, 0},
                new double[] {1, 1},

        };

        double[][] expectedResults = new double[][] {

                new double[] {0,1},
                new double[] {1,0},
                new double[] {1,0},
                new double[] {1,1},

        };



        Random r = new Random();
        for (int i = 0; i < 2000000; i++) {
            int j = r.nextInt(0,4);
            network.train(features[j], expectedResults[j], eta, ef);
        }
        System.out.println(Arrays.toString(network.calc(features[0])));
        System.out.println(Arrays.toString(network.calc(features[1])));
        System.out.println(Arrays.toString(network.calc(features[2])));
        System.out.println(Arrays.toString(network.calc(features[3])));
    }

}
