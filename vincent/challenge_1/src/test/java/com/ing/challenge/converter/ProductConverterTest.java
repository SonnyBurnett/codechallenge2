package com.ing.challenge.converter;

import com.google.common.truth.Truth;
import com.ing.challenge.model.Product;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ProductConverterTest {
    @Test
    void isValueBelow() {
        var converter = new ProductConverter();
        assertThat(converter.filterPrice(getProduct(),10)).isTrue();
        assertThat(converter.filterPrice(getProduct(),16)).isFalse();
    }

    @Test
    void convertDollarToEuro() {
        var converter = new ProductConverter();
        assertThat(converter.convertCurrency(getProduct(),0.85).price()).isEqualTo(12.75);
    }

    @NotNull
    private Product getProduct() {
        return new Product(1, "testName", "This is a test", 15, "testing");
    }
}
