package nl.codechallenge.service;

import nl.codechallenge.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nl.codechallenge.model.Country.Belgium;
import static nl.codechallenge.model.Country.Netherlands;
import static nl.codechallenge.model.Shipper.*;

@Component
public class OrderShippingsCalculatorImpl implements OrderShippingsCalculator {

    @Override
    public List<OrderShipping> calc(List<OrderedProduct> orderedProducts) {
        Map<CustomerInfo, Double> orderWeights =
                orderedProducts.stream().collect(Collectors.groupingBy(OrderedProduct::getCustomerInfo,
                        Collectors.summingDouble(OrderedProduct::getWeight)));
        return orderWeights.entrySet().stream()
                .map(ow -> orderWeightToOrderShipping(ow.getKey(), ow.getValue()))
                .collect(Collectors.toUnmodifiableList());
    }

    private OrderShipping orderWeightToOrderShipping(CustomerInfo customerInfo, Double weight) {
        Shipper shipper = calcShipper(customerInfo.getCountry(), weight);
        Double shippingCost = calcCost(shipper, weight);
        Integer duration = calcDuration(shipper, weight);
        return new OrderShipping(customerInfo, shipper, duration, shippingCost);
    }

    private Shipper calcShipper(Country country, Double weight) {
        if (country == Netherlands && weight < 10) {
            return PostNL;
        } else if (country == Belgium) {
            return BelgioPosto;
        } else {
            return DHL;
        }
    }

    /**
     *
     * Henry, the example show a BelgioPosto of weight 2.5 with 5.7 cost, while the description states
     * "BelgioPosto and cost are 1,95 + (1 * weight)".
     * I chose to go with the description, after actually getting a failing test case :)
     */
    private Double calcCost(Shipper shipper, Double weight) {
        switch (shipper) {
            case PostNL: return 6.95;
            case BelgioPosto: return 1.95 + weight;
            case DHL: return 12.95 + 1.5 * weight;
            default: return null;
        }
    }

    /**
     *
     * Henry, the example show a BelgioPosto with 3 days duration, while the description states "for BelgioPosto = 2".
     * I chose to go with 2, after actually getting a failing test case :)
     */
    private Integer calcDuration(Shipper shipper, Double weight) {
        switch (shipper) {
            case PostNL: return 1;
            case BelgioPosto: return 2;
            case DHL: {
                if (weight < 10) {
                    return 4;
                } else {
                    return 8;
                }
            }
            default: return null;
        }
    }
}
