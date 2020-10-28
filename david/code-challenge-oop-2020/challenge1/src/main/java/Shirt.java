import java.math.BigDecimal;

public class Shirt extends Product {
    public Shirt(String productId, String name, String description, BigDecimal price) {
        super(productId, name, description, price);
    }

    @Override
    public String getCategory() {
        return "shirts";
    }
}