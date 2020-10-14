package com.ing.challenge.converter;

import com.ing.challenge.model.Product;

public class ProductConverter implements Converter<Product> {
    @Override
    public boolean filterPrice(Product product, double bound) {
        return product.price() >= bound;
    }

    @Override
    public Product convertCurrency(Product product, double rate) {
        EuroCurrencyConverter converter = new EuroCurrencyConverter(rate);
        double updatedPrice = converter.conversionTo(product.price());
        return new Product(product.productId(), product.name(), product.description(), updatedPrice, product.category());
    }
}
