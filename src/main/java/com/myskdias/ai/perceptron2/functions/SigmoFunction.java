package com.myskdias.ai.perceptron2.functions;

public class SigmoFunction implements Function {
    @Override
    public double f(double d) {
        return 1/(1 + Math.exp(-d));
    }

    @Override
    public double fp(double d) {
        double u = Math.exp(-d);
        return -u/Math.pow(1 + u, 2);
    }
}
