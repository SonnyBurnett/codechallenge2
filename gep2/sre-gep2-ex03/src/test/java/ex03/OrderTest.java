package ex03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {
    private Order testOrder = new Order();

    @Test
    void getProduct() {
        testOrder.setProduct("Test");

        assertEquals("Test", testOrder.getProduct());
    }

    @Test
    void setProduct() {
        testOrder.setProduct("Test");

        assertEquals("Test", testOrder.getProduct());
    }

    @Test
    void getPrice() {
        testOrder.setPrice(-99.99);

        assertEquals(-99.99, testOrder.getPrice());
    }

    @Test
    void setPrice() {
        testOrder.setPrice(-99.99);

        assertEquals(-99.99, testOrder.getPrice());
    }

    @Test
    void getWeight() {
        testOrder.setWeight(-999.999);

        assertEquals(-999.999, testOrder.getWeight());
    }

    @Test
    void setWeight() {
        testOrder.setWeight(-999.999);

        assertEquals(-999.999, testOrder.getWeight());
    }

    @Test
    void getCountry() {
        testOrder.setCountry("Spain");

        assertEquals("Spain", testOrder.getCountry());
    }

    @Test
    void setCountry() {
        testOrder.setCountry("Spain");

        assertEquals("Spain", testOrder.getCountry());
    }
}