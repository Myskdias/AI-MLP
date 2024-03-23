package com.myskdias.ai.perceptron.parsing;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Parser {

    private File file;

    public Parser(@NotNull File file) {
        this.file = file;
    }

    

    public Unit nextUnit() {
        return null;
    }

    public File getFile() {
        return file;
    }
}
