package nl.codechallenge.service;

import nl.codechallenge.model.OrderShipping;
import nl.codechallenge.model.OrderedProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShippingCalculatorImpl implements ShippingCalculator {
    @Override
    public List<OrderShipping> calc(List<OrderedProduct> orderedProducts) {
        return null;
    }
}
