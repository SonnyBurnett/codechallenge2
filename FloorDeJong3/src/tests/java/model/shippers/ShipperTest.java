package model.shippers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipperTest {

    @Test
    public void test_determineCosts_PostNl(){
        Shipper shipper = Shipper.POSTNL;

        assertEquals(6.95, shipper.determineCosts(0));
    }

    @Test
    public void test_determineCosts_BelgioPosto(){
        Shipper shipper = Shipper.BELGIOPOSTO;
        double weight = 5.0;
        double expectedCosts = 1.95 + weight;

        assertEquals(expectedCosts, shipper.determineCosts(weight));
    }

    @Test
    public void test_determineCosts_DHL(){
        Shipper shipper = Shipper.DHL;
        double weight = 5.0;
        double expectedCosts = 12.95 + 1.5*weight;

        assertEquals(expectedCosts, shipper.determineCosts(weight));
    }

    @Test
    public void test_determineDuration_PostNl(){
        Shipper shipper = Shipper.POSTNL;

        assertEquals(1, shipper.determineDuration(0));
    }

    @Test
    public void test_determineDuration_BelgioPosto(){
        Shipper shipper = Shipper.BELGIOPOSTO;

        assertEquals(2, shipper.determineDuration(0));
    }

    @Test
    public void test_determineDuration_DHL(){
        Shipper shipper = Shipper.DHL;

        assertEquals(4, shipper.determineDuration(6.6));
        assertEquals(8, shipper.determineDuration(10));
        assertEquals(8, shipper.determineDuration(15));
    }
}
