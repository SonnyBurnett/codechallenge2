public abstract class Product implements ProductInterface {
    String productId;
    String name;
    String description;
    int price;

    public Product(String productId, String name, String description, int price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public abstract String getCategory();
}
