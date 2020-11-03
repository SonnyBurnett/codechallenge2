package test.local.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CashConverterTest {

    @Test
    void convertDollarToEuro() {
        int dollar = 1;
        double expected = 0.85;
        double actual = CashConverter.ConvertDollarToEuro(dollar);
        assertEquals(expected, actual);
    }
}