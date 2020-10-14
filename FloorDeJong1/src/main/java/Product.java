import java.util.Comparator;

public class Product {
    private long id;
    private String name;
    private String description;
    private double price;
    private String category;

    public Product(long id, String name, String description, double price, String category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public boolean priceIsBelow(double max) {
        return this.price < max;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + description + ", " + price + ", " + category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
