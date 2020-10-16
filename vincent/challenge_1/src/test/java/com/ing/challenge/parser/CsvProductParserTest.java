package com.ing.challenge.parser;

import com.ing.challenge.model.Product;
import com.ing.challenge.util.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class CsvProductParserTest {
    @Test
    void readFile() throws URISyntaxException {
        var file = TestUtils.getFile("001-experts-inputs.csv");
        var parser = new CsvProductParser();
        List<Product> products = parser.read(file);
        assertThat(products).hasSize(3);
        assertThat(products.get(0).name()).isEqualTo("shorts");
    }

    @Test
    void readFileAsString() throws URISyntaxException {
        var file = TestUtils.getFile("001-experts-inputs.csv");
        var parser = new CsvProductParser();
        List<Product> products = parser.read(file.getAbsolutePath());
        assertThat(products).hasSize(3);
        assertThat(products.get(0).name()).isEqualTo("shorts");
    }

    @Test
    void nonExistingFile() {
        var parser = new CsvProductParser();
        List<Product> empty = parser.read("does-not-exist.csv");
        assertThat(empty).isEmpty();
    }

    @Test
    void writeFile(@TempDir Path tempDir) throws IOException {
        var file = Files.createTempFile(tempDir, "test", ".csv").toFile();
        var parser = new CsvProductParser();
        boolean bool = parser.write(file, TestUtils.getProduct());
        assertThat(bool).isTrue();
        assertThat(parser.read(file).get(0)).isEqualTo(TestUtils.getProduct());
    }

    @Test
    void writeMultipleFiles(@TempDir Path tempDir) throws IOException {
        var file = Files.createTempFile(tempDir, "test", ".csv").toFile();
        var parser = new CsvProductParser();
        var list = List.of(
                TestUtils.getProduct(),
                TestUtils.getProduct(),
                TestUtils.getProduct()
        );
        boolean bool = parser.write(file, list);
        assertThat(bool).isTrue();
        var actual = parser.read(file);
        assertThat(actual).hasSize(3);
        assertThat(actual).containsExactly(list.toArray());
    }

    @Test
    @DisplayName("write file that didn't exist.")
    void writingFileThatDidNotExist(@TempDir Path tempDir) throws IOException {
        File file = File.createTempFile(tempDir.toAbsolutePath().toString(),"file.fail");
        assertThat(file.delete()).isTrue();
        var parser = new CsvProductParser();
        boolean bool = parser.write(file, TestUtils.getProduct());
        //The CsvPrinter creates the file if it doesn't exist..
        assertThat(bool).isTrue();
    }
}
