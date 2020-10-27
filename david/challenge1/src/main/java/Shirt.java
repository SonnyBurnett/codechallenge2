public class Shirt extends Product {
    public Shirt(String productId, String name, String description, int price) {
        super(productId, name, description, price);
    }

    @Override
    public String getCategory() {
        return "shirts";
    }
}