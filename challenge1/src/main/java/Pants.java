public class Pants extends Product {
    public Pants(String productId, String name, String description, int price) {
        super(productId, name, description, price);
    }

    @Override
    public String getCategory() {
        return "pants";
    }
}
