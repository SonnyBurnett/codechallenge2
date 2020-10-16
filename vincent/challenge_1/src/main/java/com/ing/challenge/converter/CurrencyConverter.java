package com.ing.challenge.converter;

 interface CurrencyConverter {
     double getExchange();
     void setExchange (double currentRate);
     double conversionTo(double amount);
     double conversionFrom(double amount);
}
