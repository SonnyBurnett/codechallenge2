package nl.codechallenge.service;

import nl.codechallenge.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static nl.codechallenge.model.Country.*;
import static nl.codechallenge.model.Shipper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

public class OrderShippingsCalculatorImplTest {

    OrderShippingsCalculator orderShippingsCalculator = new OrderShippingsCalculatorImpl();

    @Test
    void exerciseExample() {
        List<OrderedProduct> orderedProducts = Arrays.asList(
                new OrderedProduct(new CustomerInfo(16, "Henry Been", Netherlands), "Pepernoten", 3.23, 0.5),
                new OrderedProduct(new CustomerInfo(21, "Pietje de Boer", Belgium), "Monitor", 466.19, 2.5),
                new OrderedProduct(new CustomerInfo(16, "Henry Been", Netherlands), "Jas", 128.12, 2.2)
        );

        // WHEN
        List<OrderShipping> orderShippings = orderShippingsCalculator.calc(orderedProducts);

        // THEN
        assertThat(orderShippings, hasSize(2));
        assertThat(orderShippings, containsInAnyOrder(
                new OrderShipping(new CustomerInfo(16, "Henry Been", Netherlands), PostNL, 1, 6.95),
                new OrderShipping(new CustomerInfo(21, "Pietje de Boer", Belgium), BelgioPosto, 2, 4.45)
        ));
    }

    @Test
    void addedWeightExceeding10() {
        List<OrderedProduct> orderedProducts = Arrays.asList(
                new OrderedProduct(new CustomerInfo(1, "Name", Netherlands), "Product 1", 3.23, 4.0),
                new OrderedProduct(new CustomerInfo(1, "Name", Netherlands), "Product 2", 466.19, 6.0)
        );

        // WHEN
        List<OrderShipping> orderShippings = orderShippingsCalculator.calc(orderedProducts);

        // THEN
        assertThat(orderShippings, hasSize(1));
        assertThat(orderShippings, containsInAnyOrder(
                new OrderShipping(new CustomerInfo(1, "Name", Netherlands), DHL, 8, 27.95)
        ));
    }

    @Test
    void franceLowWeight() {
        List<OrderedProduct> orderedProducts = Collections.singletonList(
                new OrderedProduct(new CustomerInfo(1, "Name", France), "Product 1", 3.23, 4.0));

        // WHEN
        List<OrderShipping> orderShippings = orderShippingsCalculator.calc(orderedProducts);

        // THEN
        assertThat(orderShippings, hasSize(1));
        assertThat(orderShippings, containsInAnyOrder(
                new OrderShipping(new CustomerInfo(1, "Name", France), DHL, 4, 12.95 + 1.5 * 4)
        ));
    }
}
