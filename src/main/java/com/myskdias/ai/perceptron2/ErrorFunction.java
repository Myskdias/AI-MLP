package com.myskdias.ai.perceptron2;

public interface ErrorFunction {

    public Function getPartialDerivative(double[] expected, int varNumber);

}
