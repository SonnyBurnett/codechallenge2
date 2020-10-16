package com.ing.challenge.converter;

import java.util.Currency;

public interface Converter<T> {
    boolean filterPrice(T obj, double bound);
    T convertCurrency(T obj, double rate);
}
