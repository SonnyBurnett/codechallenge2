package ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomersTest {
    private Customers testCustomers = new Customers();

    @BeforeEach
    void setUp() {
        testCustomers.addCustomer("1", "Sint Nicolaas");
        Order order = new Order("Stoomboot",
                1.00,
                100000.00,
                "Spain");
        testCustomers.addCustomerOrder("1", order);
        Package aPackage = new Package("Stoomboot",
                13,
                0.00,
                1000000.000);
        testCustomers.addCustomerPackage("1", aPackage);
    }

    @Test
    void addCustomer() {
        testCustomers.addCustomer("2", "Piet");

        String expectedResult = "1, Sint Nicolaas, Stoomboot, 13, 0.0\n" +
                "2, Piet, \n";

        assertEquals(expectedResult, testCustomers.generatePrintingLines());
    }

    @Test
    void addCustomerOrder() {
        Order order = new Order("Stoomboot",
                1.00,
                100000.00,
                "Spain");
        testCustomers.addCustomerOrder("2", order);

        String expectedResult = "1, Sint Nicolaas, Stoomboot, 13, 0.0\n";

        assertEquals(expectedResult, testCustomers.generatePrintingLines());
    }

    @Test
    void addCustomerPackage() {
        Package aPackage = new Package("Stoomboot",
                13,
                0.00,
                1000000.000);
        testCustomers.addCustomerPackage("2", aPackage);

        String expectedResult = "1, Sint Nicolaas, Stoomboot, 13, 0.0\n";

        assertEquals(expectedResult, testCustomers.generatePrintingLines());
    }

    @Test
    void generateCustomersPackage() {
        testCustomers.generateCustomersPackage();

        String expectedResult = "1, Sint Nicolaas, Stoomboot, 13, 0.0DHL, 8, 150012.95\n";

        assertEquals(expectedResult, testCustomers.generatePrintingLines());
    }

    @Test
    void generatePrintingLines() {
        assertEquals("1, Sint Nicolaas, Stoomboot, 13, 0.0\n", testCustomers.generatePrintingLines());
    }
}