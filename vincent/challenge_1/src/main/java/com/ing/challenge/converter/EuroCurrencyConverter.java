package com.ing.challenge.converter;

class EuroCurrencyConverter implements CurrencyConverter {


    private double Exchange;

    public EuroCurrencyConverter(double exchangeRate) {
        setExchange(exchangeRate);
    }
    @Override
    public double getExchange() {
        return Exchange;
    }
    @Override
    public void setExchange (double currentRate) {
        this.Exchange = currentRate;

    }

    @Override
    public double conversionTo(double amount) {
        return (Exchange * amount);
    }

    @Override
    public double conversionFrom(double amount) {
        return (amount / Exchange);
    }
}