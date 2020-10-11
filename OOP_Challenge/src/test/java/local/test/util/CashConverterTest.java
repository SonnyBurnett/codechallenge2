package local.test.util;

import local.test.util.CashConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashConverterTest {

    @Test
    void convertDollarToEuro() {
        int dollar = 1;
        double expected = 0.85;
        double actual = CashConverter.ConvertDollarToEuro(dollar);
        assertEquals(expected, actual);
    }
}