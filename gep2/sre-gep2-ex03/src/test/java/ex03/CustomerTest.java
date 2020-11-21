package ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.addOrderToCustomer(new Order());
        testCustomer.addPackageToCustomer(new Package());
    }

    @Test
    void test_getName() {
        assertEquals("None", testCustomer.getName());
    }

    @Test
    void test_setName() {
        testCustomer.setName("Test");

        assertEquals("Test", testCustomer.getName());
    }

    @Test
    void test_getOrdersList() {
        String expectedMessage = "\n" +
                "1\n" +
                "None, 0.0, 0.0, None\n";

        assertEquals(expectedMessage, testCustomer.getOrdersList().toString());
    }

    @Test
    void test_setOrdersList() {
        Orders newOrdersList = new Orders();
        newOrdersList.addOrder(new Order("Test Product",1.0,1.0, "Test Country" ));
        testCustomer.setOrdersList(newOrdersList);

        String expectedMessage = "\n" +
                "1\n" +
                "Test Product, 1.0, 1.0, Test Country\n";

        assertEquals(expectedMessage, testCustomer.getOrdersList().toString());
    }

    @Test
    void test_getPackagesList() {
        String expectedMessage = "\n" +
                "1\n" +
                "None, 0.0, 0.0, None\n";

        assertEquals(expectedMessage, testCustomer.getOrdersList().toString());
    }

    @Test
    void test_setPackagesList() {
        Packages newPackageList = new Packages();
        newPackageList.addPackage(new Package("Test Product",-9,-99.99, -9.9 ));
        testCustomer.setPackagesList(newPackageList);

        String expectedMessage = "\n" +
                "1\n" +
                "Test Product, -9, -99.99, -9.9\n";

        assertEquals(expectedMessage, testCustomer.getPackagesList().toString());
    }

    @Test
    void test_addOrderToCustomer() {
    }

    @Test
    void test_addPackageToCustomer() {
    }

    @Test
    void test_toString() {
        String expectedMessage = "None\n" +
                "1\n" +
                "None, 0.0, 0.0, None\n" +
                "\n" +
                "1\n" +
                "None, 0, 0.0, 0.0\n";

        assertEquals(expectedMessage, testCustomer.toString());
    }

    @Test
    void test_generatePrintingLine() {
    }
}