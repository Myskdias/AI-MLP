package com.myskdias.ai.perceptron.functions;

public class ArctanFunction implements Function {
    @Override
    public double f(double d) {
        return 2*Math.atan(d)/Math.PI;
    }

    @Override
    public double fp(double d) {
        return (2/Math.PI)*1/(1 + d*d);
    }
}
