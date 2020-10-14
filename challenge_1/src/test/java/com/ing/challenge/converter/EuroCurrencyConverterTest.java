package com.ing.challenge.converter;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class EuroCurrencyConverterTest {
    @Test
    void convertDollarFromEuro() {
        EuroCurrencyConverter converter = new EuroCurrencyConverter(0.85);
        double actual = converter.conversionFrom(10);
        assertThat(actual).isWithin(0.001).of(11.7647);
        assertThat(actual).isNotWithin(0.001).of(11.766);
        assertThat(converter.getExchange()).isEqualTo(0.85);
    }

    @Test
    void convertDollarToEuro() {
        EuroCurrencyConverter converter = new EuroCurrencyConverter(0.85);
        double actual = converter.conversionTo(10);
        assertThat(actual).isEqualTo(8.5);
    }
}
