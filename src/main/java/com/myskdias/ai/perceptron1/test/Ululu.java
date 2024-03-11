package com.myskdias.ai.perceptron1.test;

import java.util.Random;

public class Ululu {

    public static double lr = 0.01f;

    public double w_1 = 0.5f;
    public double w_2 = 0.5f;

    public double bias = 0.5f;

    public double weightedSum(double d1, double d2) {
        return sigmo(w_1 * d1 + w_2*d2 + bias);
    }

    public void update(double target, double pred, double x1, double x2) {
        w_1 +=  lr *(target - pred)*x1;
        w_2 +=  lr *(target - pred)*x2;
        bias += lr * (target - pred);
    }


    public static void main(String[] args) {

        double[] x1s = new double[] {0, 1, 0, 1};
        double[] x2s = new double[] {0, 0, 1, 1};
        double[] targets = new double[] {0, 1, 0, 0};

        Ululu u = new Ululu();
        Random r = new Random();
        for (int i = 0; i < 200000; i++) {
            int j = r.nextInt(0,4);
            u.update(targets[j], u.weightedSum(x1s[j],x2s[j] ),  x1s[j], x2s[j]);
        }
        System.out.println(u.weightedSum(0,0));
        System.out.println(u.weightedSum(0.5,0));
        System.out.println(u.weightedSum(0,1));
        System.out.println(u.weightedSum(1,1));
        System.out.println("w1: "+u.w_1);
        System.out.println("w2: "+u.w_2);
        System.out.println("b: "+u.bias);
    }

    public static double sigmo(double u) {
        return (1 / (1 + Math.exp(-u)));
    }

}
