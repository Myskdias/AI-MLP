package com.myskdias.ai.perceptron.functions;

public interface Function {

    public double f(double d);

    public default double fp(double d) {
        return 0;
    }

}
