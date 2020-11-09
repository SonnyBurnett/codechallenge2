package nl.codechallenge.io;

import nl.codechallenge.model.Field;
import nl.codechallenge.model.Game;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static nl.codechallenge.model.Field.*;

@Component
public class TicTactToeFileReaderImpl implements TicTactToeFileReader {

    @Override
    public Game read(Path file) throws IOException {
        List<Field> fields = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            Stream<String> lines = reader.lines();
            lines.forEach(line -> {
                line.chars().forEach(character -> {
                    fields.add(fieldFromChar((char) character));
                });
            });
        }
        return new Game(fields.get(0), fields.get(1), fields.get(2), fields.get(3), fields.get(4), fields.get(5),
                fields.get(6), fields.get(7), fields.get(8));
    }

    private Field fieldFromChar(char character) {
        switch (character) {
            case 'X':
                return X;
            case 'O':
                return O;
            case '.':
                return NONE;
            default:
                // this is untested as we assume the input files are in order in this exercise
                throw new RuntimeException("Unexpected char in input file: " + character);
        }
    }
}
