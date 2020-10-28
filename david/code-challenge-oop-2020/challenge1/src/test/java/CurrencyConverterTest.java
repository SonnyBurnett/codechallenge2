import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyConverterTest {
    @Test
    @DisplayName("should be able to convert the given value based on the conversion rate.")
    void convert() {
        CurrencyConverter currencyConverter = new CurrencyConverter(2f);

        assertEquals(new BigDecimal(2), currencyConverter.convert(new BigDecimal(1)));
    }
}
