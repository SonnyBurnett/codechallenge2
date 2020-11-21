package ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShippingInformationTest {
    private Orders testSetOrders = new Orders();

    @BeforeEach
    void setUp() {
        Order firstOrder = new Order("Pepernoten", 3.23, 0.5, "Netherlands");
        Order secondOrder = new Order("Jas", 128.12, 2.2, "Netherlands");
        testSetOrders.addOrder(firstOrder);
        testSetOrders.addOrder(secondOrder);
    }

    @Test
    void test_calcShipping() {
        ShippingInformation testSI = new ShippingInformation();

        String expectedMessage = "PostNL, 1, 6.95, 2.7";
        String actualMessage = testSI.calcShipping(testSetOrders).toString();

        assertEquals(expectedMessage, actualMessage);
    }
}