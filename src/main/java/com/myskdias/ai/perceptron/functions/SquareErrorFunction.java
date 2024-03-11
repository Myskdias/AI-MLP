package com.myskdias.ai.perceptron.functions;

public class SquareErrorFunction implements ErrorFunction {

    @Override
    public Function getPartialDerivative(double[] expected, int varNumber) {
        return d -> {
            return -2*(expected[varNumber] -d);
        };
    }



}
