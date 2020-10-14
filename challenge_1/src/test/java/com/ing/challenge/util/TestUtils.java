package com.ing.challenge.util;

import com.ing.challenge.model.Product;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

import static com.google.common.truth.Truth.assertThat;

public final class TestUtils {
    @NotNull
    public static File getFile(String fileName) throws URISyntaxException {
        return Path.of(
                Objects.requireNonNull(ClassLoader
                        .getSystemClassLoader()
                        .getResource(fileName))
                        .toURI()
        ).toFile();
    }

    @NotNull
    public static Product getProduct() {
        return new Product(1, "testName", "This is a test", 15, "testing");
    }

    public static void assertTestProduct(Product product) {
        assertThat(product.productId()).isEqualTo(1);
        assertThat(product.name()).isEqualTo("testName");
        assertThat(product.description()).isEqualTo("This is a test");
        assertThat(product.price()).isEqualTo(15.0);
        assertThat(product.category()).isEqualTo("testing");
    }
}
