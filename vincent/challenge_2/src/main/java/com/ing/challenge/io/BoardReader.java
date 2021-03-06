package com.ing.challenge.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BoardReader implements GameReader<char[]> {
    protected static final String CHARACTER_ERROR = "The loaded board should contain three rows with only a " +
            "'X', 'O' or '.' character.";
    protected static final Function<String, String> COULD_NOT_READ_FILE_ERROR = file ->
            "Could not read the given file: " + file + ".";

    @Override
    public char[] read(File file) throws IOException {
        if (file.exists()) {
            List<String> list = Files.lines(file.toPath())
                    .filter(line -> line.matches("[xoXO.]{3}"))
                    .collect(Collectors.toList());
            if (list.size() != 3) {
                throw new IllegalArgumentException(CHARACTER_ERROR);
            } else {
                return String.join("", list).toCharArray();
            }
        }
        throw new IOException(COULD_NOT_READ_FILE_ERROR.apply(file.toString()));
    }
}
