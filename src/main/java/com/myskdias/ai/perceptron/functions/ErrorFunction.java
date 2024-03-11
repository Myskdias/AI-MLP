package com.myskdias.ai.perceptron.functions;


public interface ErrorFunction {

    public Function getPartialDerivative(double[] expected, int varNumber);

}
