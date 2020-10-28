import java.math.BigDecimal;

public class CurrencyConverter {
    private Float conversionRate;

    CurrencyConverter(float conversionRate) {
        this.conversionRate = conversionRate;
    }

    public BigDecimal convert(BigDecimal baseCurrencyValue) {
        if (conversionRate == null) {
            throw new IllegalStateException("Conversion rate wasn't defined.");
        }
        return baseCurrencyValue.multiply(new BigDecimal(conversionRate));
    }
}
