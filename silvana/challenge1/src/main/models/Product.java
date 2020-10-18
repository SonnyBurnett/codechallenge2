package main.models;

public class Product{
    private String id;
    private String name;
    private String description;
    private Double price;
    private ECategory category;
    private ECurrency currency;

    public Product(String id, String name, String description, double price, ECategory category, ECurrency currency) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.currency = currency;
    }

    public ECurrency getCurrency() {
        return currency;
    }

    public void setCurrency(ECurrency currency) {
        this.price = this.price * currency.getConversionRate() / this.currency.getConversionRate();
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if(price >= 0) {
            this.price = price;
        } else{
            throw new Error("The product price cannot have a negative value!");
        }

    }
    public ECategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}