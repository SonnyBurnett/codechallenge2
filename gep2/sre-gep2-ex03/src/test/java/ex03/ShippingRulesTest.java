package ex03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingRulesTest {

    @Test
    void test_filterShippingRuleList() {
        List<ShippingRule> result = ShippingRules.filterShippingRuleList("Netherlands");

        assertEquals(8, result.size());
    }
}