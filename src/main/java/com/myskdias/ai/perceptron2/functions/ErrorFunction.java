package com.myskdias.ai.perceptron2.functions;


public interface ErrorFunction {

    public Function getPartialDerivative(double[] expected, int varNumber);

}
