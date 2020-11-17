package com.ing.challenge.io;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class CsvWriterTest {
    @Test
    void cantWriteToCsv() {
        var file = Paths.get("src/test/resources", "faulty//File///.txt").toFile();
        var writer = new CsvWriter();
        var iterable = List.of("stuff");
        var err = Assertions.assertThrows(IOException.class, () -> writer.write(file,iterable));
        Truth.assertThat(err).hasMessageThat().isEqualTo(CsvWriter.ERROR_MESSAGE);
    }
    @Test
    void writeToFile() throws IOException {
        var file = Paths.get("src/test/resources", "file.txt").toFile();
        Truth.assertThat(file.exists()).isFalse();
        var writer = new CsvWriter();
        var iterable = List.of("stuff");
        writer.write(file,iterable);
        Truth.assertThat(file.exists()).isTrue();
        Truth.assertThat(file.delete()).isTrue();
    }
}
