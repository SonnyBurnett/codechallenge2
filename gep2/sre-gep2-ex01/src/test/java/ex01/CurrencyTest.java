package ex01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyTest {

    private BigDecimal dollar;
    private BigDecimal euro;

    @BeforeEach
    void setUp() {
        dollar = Currency.DOLLAR.toEuro(new BigDecimal("1.00"));
        euro = Currency.EURO.toDollar(new BigDecimal("0.85"));
    }

    @Test
    void toEuro() {
        dollar = Currency.DOLLAR.toEuro(new BigDecimal("1.00"));
        dollar = dollar.setScale(2, RoundingMode.HALF_EVEN);
        assertEquals("0.85", dollar.toString());
        dollar = Currency.DOLLAR.toEuro(new BigDecimal("0.00"));
        dollar = dollar.setScale(2, RoundingMode.HALF_EVEN);
        assertEquals("0.00", dollar.toString());
    }

    @Test
    void toDollar() {
        euro = Currency.EURO.toDollar(new BigDecimal("0.85"));
        assertEquals("1.00", euro.toString());
        euro = Currency.EURO.toDollar(new BigDecimal("0.00"));
        assertEquals("0.00", euro.toString());
    }
}