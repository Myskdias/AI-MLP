package com.myskdias.ai.perceptron.parsing;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Iterator;

public class Parser {

    private File file;
    private Unit unit;

    public Parser(@NotNull File file) {
        this.file = file;
        try {
            this.unit = new Unit(readFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Unit getBaseUnit() {
        return unit;
    }

    public Iterator<Unit> getUnitIterator() {
        return unit.iterator();
    }

    public File getFile() {
        return file;
    }

    private String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[10];
        while (reader.read(buffer) != -1) {
            stringBuilder.append(new String(buffer));
            buffer = new char[10];
        }
        reader.close();

        return stringBuilder.toString();
    }
}
