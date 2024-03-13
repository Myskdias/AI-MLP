package com.myskdias.ai.perceptron.functions;

public class ReluFunction implements Function {
    @Override
    public double f(double d) {
        return Math.max(0, d);
    }

    @Override
    public double fp(double d) {
        if(d < 0) return 0;
        else return 1;
    }
}
