package ex03;

import org.javatuples.Triplet;

import java.util.LinkedList;
import java.util.List;

public class ShippingRule {
    private final String nameShippingCompany;
    private final String operatingCountry;
    private final List<Triplet<String, Integer, Double>> weightDuration;
    private final double baseCost;
    private final double weightMultiplier;

    @SuppressWarnings("unused")
    public ShippingRule() {
        this.nameShippingCompany = "None";
        this.operatingCountry = "None";
        this.weightDuration = new LinkedList<>();
        this.setWeightDuration("None", 0, 0.0);
        this.baseCost = 0;
        this.weightMultiplier = 0;
    }

    public ShippingRule(String name, String country, Double baseCost, Double weightMultiplier) {
        this.nameShippingCompany = name;
        this.operatingCountry = country;
        this.weightDuration = new LinkedList<>();
        this.baseCost = baseCost;
        this.weightMultiplier = weightMultiplier;
    }

    public void setWeightDuration(String operator, Integer duration, Double weight) {
        this.weightDuration.add(new Triplet<>(operator, duration, weight));
    }

    public String getOperatingCountry() {
        return operatingCountry;
    }

    public String getNameShippingCompany() {
        return nameShippingCompany;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public double getWeightMultiplier() {
        return weightMultiplier;
    }

    public String toString() {
        return ("name: " + this.nameShippingCompany +
                "\ncountry: " + this.operatingCountry +
                "\nbaseCost: " + this.baseCost +
                "\nweightMultiplier: " + this.weightMultiplier +
                "\ndetails: " + this.weightDuration.toString());
    }

    public Integer getWeightDuration(Double weight) {
        Integer bestDuration = 0;
        for (Triplet<String, Integer, Double> detail : this.weightDuration) {
            Double dw = detail.getValue2();
            if (detail.getValue0().equals("<") && weight.compareTo(dw) < 0) bestDuration = detail.getValue1();
            else if (detail.getValue0().equals(">") && weight.compareTo(dw) > 0)
                bestDuration = detail.getValue1();
            else if (detail.getValue0().equals("=") && weight.compareTo(dw) == 0) {
                bestDuration = detail.getValue1();
            }
        }
        return bestDuration;
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }
        if (!(object instanceof ShippingRule)) {
            return false;
        }

        ShippingRule other = (ShippingRule) object;

        return this.nameShippingCompany.equals(other.nameShippingCompany)
                && this.operatingCountry.equals(other.operatingCountry);
    }

    @Override
    public final int hashCode() {
        int hashResult = 17;

        if (nameShippingCompany != null) {
            hashResult = 31 * hashResult + nameShippingCompany.hashCode();
        }
        if (operatingCountry != null) {
            hashResult = 31 * hashResult + operatingCountry.hashCode();
        }

        return hashResult;
    }
}