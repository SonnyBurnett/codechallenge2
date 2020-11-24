package ex03;

public class Package {
    private String Shipper;
    private Integer Duration;
    private Double ShippingCost;
    private Double TotalWeight;

    public Double getTotalWeight() {
        return TotalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        TotalWeight = totalWeight;
    }

    public Package() {
        setShipper("None");
        setDuration(0);
        setShippingCost(0.00);
        setTotalWeight(0.0);
    }

    @SuppressWarnings("unused")
    public Package(String shipper, Integer duration, Double shippingCost, Double totalWeight) {
        setShipper(shipper);
        setDuration(duration);
        setShippingCost(shippingCost);
        setTotalWeight(totalWeight);
    }

    public String getShipper() {
        return Shipper;
    }

    public void setShipper(String shipper) {
        Shipper = shipper;
    }

    public Integer getDuration() {
        return Duration;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public Double getShippingCost() {
        return ShippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        ShippingCost = shippingCost;
    }

    public String toString(){
        return getShipper() + ", " +
                getDuration() + ", " +
                getShippingCost().toString() + ", " +
                getTotalWeight().toString();
    }
}