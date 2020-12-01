package nl.codechallenge.service;

import nl.codechallenge.model.OrderShipping;
import nl.codechallenge.model.OrderedProduct;

import java.util.List;

public interface OrderShippingsCalculator {
    List<OrderShipping> calc(List<OrderedProduct> orderedProducts);
}
