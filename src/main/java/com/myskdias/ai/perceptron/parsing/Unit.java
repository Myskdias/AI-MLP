package com.myskdias.ai.perceptron.parsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Unit {

    protected List<Unit> subUnit = new ArrayList<>();

    protected String value;

    public Iterator<Unit> getSubUnit() {
        return subUnit.iterator();
    }

    public String getValue() {
        return value;
    }
}
