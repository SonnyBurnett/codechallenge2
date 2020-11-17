package com.ing.challenge.io;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class BoardReaderTest {
    @Test
    void illegalArgCheck() throws IOException {
        var file = Paths.get("src/test/resources", "007-failing.txt").toFile();
        var reader = new BoardReader();
        var err = Assertions.assertThrows(IllegalArgumentException.class, () -> reader.read(file));
        Truth.assertThat(err).hasMessageThat().isEqualTo(BoardReader.CHARACTER_ERROR);
    }
    @Test
    void ioErrorCheck() throws IOException {
        var file = Paths.get("src/test/resources", "failing.txt").toFile();
        var reader = new BoardReader();
        var err = Assertions.assertThrows(IOException.class, () -> reader.read(file));
        Truth.assertThat(err).hasMessageThat().isEqualTo(BoardReader.COULD_NOT_READ_FILE_ERROR.apply(file.toString()));
    }

    @Test
    void readAFile() throws IOException {
        var file = Paths.get("src/test/resources", "006-O-is-next.txt").toFile();
        var reader = new BoardReader();
        var boardArray = reader.read(file);
        Truth.assertThat(boardArray).asList().hasSize(9);
    }
}
