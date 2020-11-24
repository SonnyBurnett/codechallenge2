package ex03;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ShippingRules {
    private static final List<ShippingRule> shippingRuleList = new LinkedList<>();

    public ShippingRules() {
        ShippingRule temp = new ShippingRule("PostNL",
                "Netherlands",
                6.95,
                0.00);
        temp.setWeightDuration("<", 1, 10.0);
        shippingRuleList.add(temp);
        temp = new ShippingRule("BelgioPost",
                "Belgium",
                1.95,
                1.0);
        temp.setWeightDuration(">", 2, 0.0);
        shippingRuleList.add(temp);
        temp = new ShippingRule("DHL",
                "ALL",
                12.95,
                1.5);
        temp.setWeightDuration("<", 4, 10.0);
        temp.setWeightDuration("=", 8, 10.0);
        temp.setWeightDuration(">", 8, 10.0);
        shippingRuleList.add(temp);
    }

    public static List<ShippingRule> filterShippingRuleList(String country) {
        return shippingRuleList
                .stream()
                .filter(r -> r.getOperatingCountry().equals(country) ||
                        r.getOperatingCountry().equals("ALL"))
                .distinct()
                .collect(Collectors.toList());
    }
}