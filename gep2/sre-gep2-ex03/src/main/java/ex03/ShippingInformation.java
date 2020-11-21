package ex03;

import java.util.List;

public class ShippingInformation {

    public ShippingInformation() {
        //noinspection InstantiationOfUtilityClass
        new ShippingRules();
    }

    public Package calcShipping(Orders orders){
        Package thePackage = new Package();
        thePackage.setTotalWeight(orders.sumOrdersWeight());
        List<ShippingRule> possible = ShippingRules.filerShippingRuleList(orders.ordersCountry());

        for (ShippingRule rule : possible) {
            if (thePackage.getShippingCost() >
                    rule.getBaseCost()+ rule.getWeightMultiplier()* thePackage.getTotalWeight()
                    || thePackage.getShippingCost().compareTo(0.00) == 0) {
                thePackage.setShippingCost(rule.getBaseCost()+ rule.getWeightMultiplier() * thePackage.getTotalWeight());
                thePackage.setShipper(rule.getNameShippingCompany());
                thePackage.setDuration(rule.getWeightDuration(thePackage.getTotalWeight()));
            }
        }

        return thePackage;
    }
}