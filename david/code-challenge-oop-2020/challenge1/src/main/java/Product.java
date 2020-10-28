import java.math.BigDecimal;

public abstract class Product implements ProductInterface {
    String productId;
    String name;
    String description;
    BigDecimal price;

    public Product(String productId, String name, String description, BigDecimal price) {
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

    public BigDecimal getPrice() {
        return this.price;
    }

    public void convertPrice(CurrencyConverter converter) {
        this.price = converter.convert(this.price);
    }

    public abstract String getCategory();
}
