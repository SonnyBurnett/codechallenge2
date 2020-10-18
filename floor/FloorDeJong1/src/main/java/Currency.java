import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Currency {

    static final Logger LOGGER = LoggerFactory.getLogger(Currency .class);

    public enum Type
    {
        USD, EURO, GBP
    }

    public static Map<Type, Double> usdRates = new HashMap<>();
    static {
        usdRates.put(Type.USD, 1.0);
        usdRates.put(Type.EURO, 0.85);
        usdRates.put(Type.GBP, 0.77);
    }

    private Type currencyType;
    private Map<Type, Double> conversionRates = new HashMap<>();

    public Currency(Type type){
        setConversionRates(type);
        this.currencyType = type;
    }

    public Currency() {
        this(Type.USD);
    }

    private void setConversionRates(Type currencyType) {
        if (this.currencyType == null || this.currencyType != currencyType) {
            conversionRates.clear();
            Double rate = usdRates.get(currencyType);

            for (Type key : usdRates.keySet()) {
                conversionRates.put(key, usdRates.get(key) / rate);
            }
        }
    }

    public Type getCurrencyType() {
        return currencyType;
    }

    public Map<Type, Double> getConversionRates() {
        return this.conversionRates;
    }

    public void setCurrencyType(Type currencyType) {
        if (this.currencyType != currencyType) {
            setConversionRates(currencyType);
            this.currencyType = currencyType;
        } else {
            LOGGER.warn("Currency unchanged, is already " + currencyType);
        }
    }

    public Double getRate(Type currencyType) {
        return conversionRates.get(currencyType);
    }
}
