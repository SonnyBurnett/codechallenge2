package ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShippingRuleTest {
    private ShippingRule testShippingRule = new ShippingRule();

    @BeforeEach
    void setUp() {
        this.testShippingRule = new ShippingRule("PostNL",
                "Netherlands",
                6.95,
                0.00);
        testShippingRule.setWeightDuration("<", 1, 10.0);
    }

    @Test
    void test_setWeightDuration() {
        testShippingRule.setWeightDuration("test", -999, -999.99);

        String expectedMessage = "name: PostNL\n" +
                "country: Netherlands\n" +
                "baseCost: 6.95\n" +
                "weightMultiplier: 0.0\n" +
                "details: [[<, 1, 10.0], [test, -999, -999.99]]";
        String actualMessage = testShippingRule.toString();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void test_getOperatingCountry() {
        assertEquals("Netherlands", testShippingRule.getOperatingCountry());
    }

    @Test
    void test_getNameShippingCompany() {
        assertEquals("PostNL", testShippingRule.getNameShippingCompany());
    }

    @Test
    void test_getBaseCost() {
        assertEquals(6.95, testShippingRule.getBaseCost());
    }

    @Test
    void test_getWeightMultiplier() {
        assertEquals(0.0, testShippingRule.getWeightMultiplier());
    }

    @Test
    void test_ToString() {
        String expectedMessage = "name: PostNL\n" +
                "country: Netherlands\n" +
                "baseCost: 6.95\n" +
                "weightMultiplier: 0.0\n" +
                "details: [[<, 1, 10.0]]";
        String actualMessage = testShippingRule.toString();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void test_getWeightDuration() {
        assertEquals(1, testShippingRule.getWeightDuration(1.0));
    }
}