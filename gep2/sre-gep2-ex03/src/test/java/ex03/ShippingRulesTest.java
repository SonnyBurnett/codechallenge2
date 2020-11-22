package ex03;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingRulesTest {

    @Test
    void test_filterShippingRuleList() {
        @SuppressWarnings({"InstantiationOfUtilityClass", "unused"})
        AtomicReference<ShippingRules> loadShippingRules = new AtomicReference<>(new ShippingRules());
        List<ShippingRule> result = ShippingRules.filterShippingRuleList("Netherlands");

        String expectedResult = "[name: PostNL\n" +
                "country: Netherlands\n" +
                "baseCost: 6.95\n" +
                "weightMultiplier: 0.0\n" +
                "details: [[<, 1, 10.0]], name: DHL\n" +
                "country: ALL\n" +
                "baseCost: 12.95\n" +
                "weightMultiplier: 1.5\n" +
                "details: [[<, 4, 10.0], [=, 8, 10.0], [>, 8, 10.0]]]";

        assertEquals(expectedResult, result.toString());
    }
}