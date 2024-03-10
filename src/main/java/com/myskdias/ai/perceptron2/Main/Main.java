package com.myskdias.ai.perceptron2.Main;

import com.myskdias.ai.perceptron2.NetworkBuilder;
import com.myskdias.ai.perceptron2.functions.ArctanFunction;
import com.myskdias.ai.perceptron2.functions.SigmoFunction;

public class Main {

    public static void main(String[] args) {
        NetworkBuilder networkBuilder = new NetworkBuilder(2, 1, new ArctanFunction(), new SigmoFunction());
    }

}
