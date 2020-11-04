import java.math.BigDecimal;

public class Pants extends Product {
    public Pants(String productId, String name, String description, BigDecimal price) {
        super(productId, name, description, price);
    }

    @Override
    public String getCategory() {
        return "pants";
    }
}
