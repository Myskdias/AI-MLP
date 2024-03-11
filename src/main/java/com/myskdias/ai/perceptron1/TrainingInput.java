package com.myskdias.ai.perceptron1;

public class TrainingInput {

    private final float[] values;
    private final float[] results;

    public TrainingInput(float[] values, float[] results) {
        this.values = values;
        this.results = results;
    }

    public float[] getValues() {
        return values;
    }

    public float[] getResults() {
        return results;
    }

    public float getValue(int i) {
        return values[i];
    }

    public float getResult(int j) {
        return results[j];
    }
}
