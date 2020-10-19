package ex01;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Currency supports dollar and euro, implementation is based on BigDecimal as that does not introduce
 * rounding issues (@see gep2/sre-gep2-ex01/README.md). The following values can be used.
 * {@link #DOLLAR}
 * {@link #EURO}
 *
 * In the enumeration I use a neat trick with overriding the public methods with implemented conversion logic.
 */

public enum Currency {
    DOLLAR("dollar") {
        @Override
        public BigDecimal toEuro(BigDecimal value) {
            value = value.multiply(rateDollarEuro);
            return value.setScale(2, RoundingMode.HALF_EVEN);
        }
    },

    EURO("euro") {
        @Override
        public BigDecimal toDollar(BigDecimal value) {
            value = value.multiply(rateEuroDollar);
            return value.setScale(2, RoundingMode.HALF_EVEN);
        }
    };

    final static BigDecimal rateDollarEuro = new BigDecimal("0.85");
    final static BigDecimal rateEuroDollar = new BigDecimal(Double.toString(1 / 0.85));

    private final String currency;

    public BigDecimal toEuro(BigDecimal value) {
        return value;
    }

    public BigDecimal toDollar(BigDecimal value) {
        return value;
    }

    Currency(String currency) {
        this.currency = currency;
    }

    @SuppressWarnings("unused")
    public String getCurrency() {
        return currency;
    }
}

