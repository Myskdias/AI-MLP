package com.myskdias.ai.perceptron2.backtracking;

import com.myskdias.ai.perceptron2.ErrorFunction;
import com.myskdias.ai.perceptron2.Function;

public class ToolBox {

    /**
     * calculate \delta_i^s
     * @param prevDeltaLs the \delta_l^{s+1} where s+1 designates the s+1 layer (where layer 0 is the entry level)
     * @param prevOmegaILs the weight linking the neuron (i, s) to the neurons of the layer s+1 : \omega_{i,l}^{s+1}
     * @param fs the activation function of the layer s, we only use its derivative
     * @param zis z_i^s : the weighted sum of the neuron (i,s)
     * @return \delta_i^s
     */
    public static double calcDeltaIS(double[] prevDeltaLs, double[] prevOmegaILs, Function fs, double zis) {

        int N = prevDeltaLs.length;
        if(N != prevOmegaILs.length) {
            System.err.println("Wrong size !!!!!!!!");
            return Double.NaN;
        }
        double sum = 0;
        for (int l = 0; l < N; l++) {
            sum += prevDeltaLs[l] * prevOmegaILs[l];
        }
        return sum * fs.fp(zis);
    }

    /**
     *
     * @param deltaIS see {@link #calcDeltaIS(double[], double[], Function, double)}
     * @param xj = y_j^{s-1}
     * @param eta learning rate
     * @return the delta to retrieve to \omega_j^i
     */
    public static double delta(double deltaIS, double xj, double eta) {
        return deltaIS*xj*eta;
    }

    public static double newOmega(double prevOmega, double delta) {
        return prevOmega - delta;
    }

    /**
     *
     * @param ef the error function of the neural network
     * @param expected the expected results that our network should return with the set of values we gave it
     * @param i the same i as in z_i^{s_{max}}
     * @param activationFunction the activation function of the last layer
     * @param zismax : z_i^{s_{max}} it s the weighted sum
     * @return \delta_i^{s_{max}}
     */
    public static double calcDeltaISMax(ErrorFunction ef, double[] expected, int i, Function activationFunction, double zismax) {
        return ef.getPartialDerivative(expected, i).f(activationFunction.f(zismax))
                * activationFunction.fp(zismax);
    }

}
