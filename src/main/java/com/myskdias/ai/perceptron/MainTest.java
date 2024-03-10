package com.myskdias.ai.perceptron;

import com.myskdias.ai.perceptron.Arete;
import com.myskdias.ai.perceptron.Neurone;
import com.myskdias.ai.perceptron.Reseau;
import com.myskdias.ai.perceptron.ReseauBuilder;
import com.myskdias.ai.perceptron.TrainingInput;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.*;

public class MainTest {

    public static void main(String[] args) {
        Reseau r = new ReseauBuilder().addLayer(2).addLayer(2).build();

        TrainingInput t1 = new TrainingInput(new float[] {1f,0f}, new float[] {0f, 1f});
        TrainingInput t2 = new TrainingInput(new float[] {1f,1f}, new float[] {1f, 0f});
        TrainingInput t3 = new TrainingInput(new float[] {0f,1f}, new float[] {0f, 1f});
        TrainingInput t4 = new TrainingInput(new float[] {0f,0f}, new float[] {0f, 1f});

        TrainingInput[] dataSet = new TrainingInput[] {
                t1, t2, t3, t4
        };

        for (int i = 0; i < 50; i++) {
            TrainingInput trainingInput = getRandom(dataSet);
            float[] results = r.calc(trainingInput.getValues());
            for (int j = 0; j < results.length; j++) {

                for (int k = 0; k < r.getFinalLayer().length; k++) {
                    Neurone n = r.getFinalLayer()[k];
                    for (int l = 0; l < n.getIn().length; l++) {
                        Arete arete = n.getIn()[l];
                        arete.setW(update(arete.getW(), results[k], trainingInput.getResult(k), 1f, trainingInput.getValue(l)));

                    }
                }

            }
        }
        System.out.println("Trained !!");
        System.out.println(Arrays.toString(r.calc(t1.getValues())));

    }

    public float erreur(float result, float expected) {
        return (float) (-expected*log(result) - (1-expected)*log(1- result));
    }

    public static float update(float previous, float result, float expected, float eta, float input) {
        return previous - eta*(expected - result)*input;
    }

    public static <T> T getRandom(T[] tArray) {
        Random r = new Random();
        return tArray[r.nextInt(0, tArray.length)];
    }

}
