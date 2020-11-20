package model.shippers;

import model.customer.Customer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


public class ShippingInfoDeterminerTest {

    private Customer customer = spy(new Customer(1, "name", "country"));

    private ShippingInfoDeterminer determiner = spy(new ShippingInfoDeterminer());

    @Test
    public void test_determineInfo_perCustomer_belgium() {
        doReturn("belgium").when(customer).getCountry();

        determiner.determineInfo(customer);
        verify(customer, times(1)).setShipper(Shipper.BELGIOPOSTO);
    }

    @Test
    public void test_determineInfo_perCustomer_netherlandsBelow10() {
        doReturn("netherlands").when(customer).getCountry();
        doReturn(6.6).when(customer).getTotalWeight();

        determiner.determineInfo(customer);
        verify(customer, times(1)).setShipper(Shipper.POSTNL);
    }

    @Test
    public void test_determineInfo_perCustomer_netherlands_weight10() {
        doReturn("netherlands").when(customer).getCountry();
        doReturn(10.).when(customer).getTotalWeight();

        determiner.determineInfo(customer);
        verify(customer, times(1)).setShipper(Shipper.DHL);
    }

    @Test
    public void test_determineInfo_perCustomer_nederlands_weighAbove10() {
        doReturn("netherlands").when(customer).getCountry();
        doReturn(12.5).when(customer).getTotalWeight();

        determiner.determineInfo(customer);
        verify(customer, times(1)).setShipper(Shipper.DHL);
    }

    @Test
    public void test_determineInfo_perCustomer_otherCountry() {
        doReturn("germany").when(customer).getCountry();

        determiner.determineInfo(customer);
        verify(customer, times(1)).setShipper(Shipper.DHL);
    }

    @Test
    public void test_determineInfo_database() {
        int size = 4;

        Map<Long, Customer> database = new HashMap<>();
        for (long i=0L; i<size; i++) {
            database.put(i, customer);
        }

        determiner.determineInfo(database);

        verify(determiner, times(size)).determineInfo(customer);
    }
}
