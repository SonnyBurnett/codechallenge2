package com.ing.challenge.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static com.google.common.truth.Truth.assertThat;

public class ProductTest {
    @Test
    void createBasicProduct() {
        Product product = getProduct();
        assertTestProduct(product);
    }

    @Test
    void createProductFromCsv() {
        try (Reader in = new FileReader(getFile("001-experts-inputs.csv"))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreSurroundingSpaces()
                    .parse(in);
            for (var record : records) {
                var product = Product.fromCsv(record);
                assertThat(product).isInstanceOf(Product.class);
            }

        } catch (IOException | URISyntaxException e) {
            Assertions.fail("Error while parsing csv", e);
        }
    }

    @ValueSource(strings = {"002-fail-on-price-numbers.csv", "003-fail-on-productid-numbers.csv"})
    @ParameterizedTest
    @DisplayName("create product from CSV with error(s) using this {0} file")
    void createProductFromCsvWithError(String csv) {
        try (Reader in = new FileReader(getFile(csv))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreSurroundingSpaces()
                    .parse(in);
            for (var record : records) {
                var err = Assertions.assertThrows(IllegalArgumentException.class, () -> Product.fromCsv(record));
                assertThat(err).hasMessageThat().isEqualTo("value(s) could not be parsed to an Integer");
            }

        } catch (IOException | URISyntaxException e) {
            Assertions.fail("Error while parsing csv", e);
        }
    }

    @Test
    void createProductFromCsvLackingAField() {
        try (Reader in = new FileReader(getFile("004-fail-on-fields.csv"))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreSurroundingSpaces()
                    .parse(in);
            for (var record : records) {
                var err = Assertions.assertThrows(IllegalArgumentException.class, () -> Product.fromCsv(record));
                assertThat(err).hasMessageThat().isEqualTo("Not all records could be found. Missing: [category]");
            }

        } catch (IOException | URISyntaxException e) {
            Assertions.fail("Error while parsing csv", e);
        }
    }

    @Test
    void createNewRecordBasedOnProduct(@TempDir Path tempDir) throws IOException {
        Product product = getProduct();
        var file = Files.createTempFile(tempDir,"test",".csv");
        product.toCsv(file.toFile());

        try (Reader in = new FileReader(file.toFile())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreSurroundingSpaces()
                    .parse(in);
            for (var record : records) {
                var actual = Product.fromCsv(record);
                assertTestProduct(actual);
            }

        } catch (IOException e) {
            Assertions.fail("Error while parsing csv", e);
        }
    }

    @NotNull
    private File getFile(String fileName) throws URISyntaxException {
        return Path.of(
                Objects.requireNonNull(ClassLoader
                        .getSystemClassLoader()
                        .getResource(fileName))
                        .toURI()
        ).toFile();
    }

    @NotNull
    private Product getProduct() {
        return new Product(1, "testName", "This is a test", 15, "testing");
    }

    private void assertTestProduct(Product product) {
        assertThat(product.productId()).isEqualTo(1);
        assertThat(product.name()).isEqualTo("testName");
        assertThat(product.description()).isEqualTo("This is a test");
        assertThat(product.price()).isEqualTo(15.0);
        assertThat(product.category()).isEqualTo("testing");
    }
}
