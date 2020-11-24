package ex03;

public class Order {
    private String Product;
    private Double Price;
    private Double Weight;
    private String Country;

    @SuppressWarnings("unused")
    public Order() {
        setProduct("None");
        setPrice(0.00);
        setWeight(0.0);
        setCountry("None");
    }

    public Order(String product, Double price, Double weight, String country) {
        setProduct(product);
        setPrice(price);
        setWeight(weight);
        setCountry(country);
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

}