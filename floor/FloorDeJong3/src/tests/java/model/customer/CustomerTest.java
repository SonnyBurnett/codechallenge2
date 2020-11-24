package model.customer;

import model.product.Product;
import model.shippers.Shipper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerTest {

    private Product product = mock(Product.class);

    private Long id = 1L;
    private String name = "Name";
    private String country = "Country";
    private Customer customer = spy(new Customer(id, name, country));

    private Shipper shipper = mock(Shipper.class);

    @Test
    public void test_getTotalWeight() {
        double weight1 = 1.0;
        double weight2 = 4.5;

        customer.addProduct(product);
        customer.addProduct(product);
        when(product.getWeight()).thenReturn(weight1).thenReturn(weight2);

        assertEquals(weight1 + weight2, customer.getTotalWeight());
    }

    @Test
    public void test_setShipper() {
        double costs = 2.0;
        int duration = 1;

        when(shipper.determineCosts(anyDouble())).thenReturn(costs);
        when(shipper.determineDuration(anyDouble())).thenReturn(duration);

        customer.setShipper(shipper);

        verify(customer, times(1)).setShippingCosts(costs);
        verify(customer, times(1)).setDuration(duration);
    }

    @Test
    public void test_getShippingInfo(){
        double weight = 5.0;
        when(customer.getTotalWeight()).thenReturn(weight);
        Shipper shipper = Shipper.BELGIOPOSTO;
        customer.setShipper(shipper);

        String expectedResult = String.format("%s,%s,%s,%s,%s", id, name, shipper, shipper.determineDuration(weight)
                , shipper.determineCosts(weight));

        assertEquals(expectedResult, customer.getShippingInfo());
    }
}
