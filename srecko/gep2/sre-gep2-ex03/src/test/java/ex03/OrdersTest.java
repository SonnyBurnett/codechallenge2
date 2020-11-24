package ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {
    private Orders testSetOrders = new Orders();

    @BeforeEach
    void setUp() {
        Order firstOrder = new Order("Pepernoten", 3.23, 0.5, "Netherlands");
        testSetOrders.addOrder(firstOrder);
    }

    @Test
    void test_addOrder() {
        Order secondOrder = new Order("Jas", 128.12, 2.2, "Netherlands");
        testSetOrders.addOrder(secondOrder);

        String expectedMessage = "\n" +
                "2\n" +
                "Pepernoten, 3.23, 0.5, Netherlands\n" +
                "Jas, 128.12, 2.2, Netherlands\n";
        String actualMessage = testSetOrders.toString();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void test_toString() {
        String expectedMessage = "\n" +
                "1\n" +
                "Pepernoten, 3.23, 0.5, Netherlands\n";

        assertEquals(expectedMessage, testSetOrders.toString());
    }

    @Test
    void test_sumOrdersWeight() {
        Order secondOrder = new Order("Jas", 128.12, 2.2, "Netherlands");
        testSetOrders.addOrder(secondOrder);

        assertEquals(2.7, testSetOrders.sumOrdersWeight());
    }

    @Test
    void test_ordersCountry() {
        Order secondOrder = new Order("Jas", 128.12, 2.2, "Netherlands");
        testSetOrders.addOrder(secondOrder);

        assertEquals("Netherlands", testSetOrders.ordersCountry());
    }
}