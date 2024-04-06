package com.myskdias.ai.perceptron.parsing;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Unit implements Iterable<Unit> {

    protected List<Unit> subUnit = new ArrayList<>();

    protected final String value;

    public Unit() {
        this.value = null;
    }

    public Unit(String value) {
        this.value = value;
    }

    /**
     *
     * @param s the whole sequence
     * @param beginning the beginning index of this sequence
     * @return the end index + 1 of this unit
     */
    public int process(char[] s, int beginning) {

        int i = beginning;
        while(i < s.length && s[i] != '}') {
            if(s[i] == '{') {
                //diving into another sub-unit
                Unit sub = new Unit();
                i = sub.process(s, beginning + 1);
                subUnit.add(sub);
            } else if(s[i] == ',') {
                i+= 2;
            } else {
                //if we are here it means that there is a real concrete value
                int bi = i;
                while(i < s.length) {

                    if(s[i] == '}') {
                        //if we are here it means that we reach the end of the list
                        String value = new String(Arrays.copyOfRange(s, bi, i));
                        Unit unit = new Unit(value);
                        subUnit.add(unit);
                        return i++;
                    } else if(s[i] == ',') {
                        //we reach the end of this element
                        String value = new String(Arrays.copyOfRange(s, bi, i));
                        Unit unit = new Unit(value);
                        subUnit.add(unit);
                        i += 2;
                        break;
                    }
                    i++;
                }
            }
        }
        return i+1;
    }

    public String getValue() {
        return value;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Unit> iterator() {
        return subUnit.iterator();
    }

    /**
     *
     * @return the number of subunit
     */
    public int getSize() {
        return subUnit.size();
    }

}
