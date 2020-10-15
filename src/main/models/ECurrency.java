package main.models;

public enum ECurrency {
    USD(1.0),
    EUR(0.85);

    private Double conversionRate;

    private ECurrency(double conversionRate) {
        this.conversionRate = conversionRate;
    }
    public Double getConversionRate() {
        return conversionRate;
    }

}
