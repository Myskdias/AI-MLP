package com.myskdias.ai.perceptron.functions;

public class SigmoFunction implements Function {
    @Override
    public double f(double d) {
        double r =  1/(1 + Math.exp(-d));
        if(r != r) {
            throw new RuntimeException("this should not happen, d: "+d);
        }
        return r;
    }

    @Override
    public double fp(double d) {
        double u = Math.exp(-d);
        double r = u/Math.pow(1 + u, 2);
        if(r != r) {
            throw new RuntimeException("this shouldn't happen, d: "+d);
        }
        return r;
    }
}
