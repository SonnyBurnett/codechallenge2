import java.util.Arrays;

public class Product implements Cloneable, Comparable<Product> {
    private long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String currency;

    public static final String PRODUCT_INFO = "productId, name, description, price, category";

    public Product(long id, String name, String description, double price, String category, String currency){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.currency = currency.toUpperCase();
    }

    public Product(long id, String name, String description, double price, String category){
        this(id, name, description, price, category, "DOLLAR");
    }

    public boolean checkPriceBelow(double max) {
        return this.price < max;
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(this.price, o.getPrice());
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
       return (Product) super.clone();
    }

    public static boolean checkProductInfo(String info) {
        String[] lineList = info.split(", ");
        String[] productInfo = PRODUCT_INFO.split(", ");

        if (Arrays.compare(lineList, productInfo) == 0) {
            return true;
        }
        System.out.println("Error: Product information is not correct.\n" + Arrays.toString(lineList));
        return false;
    }

    public void convertCurrency(String newCurrency, double rate) {
        if (!this.currency.equals(newCurrency.toUpperCase())) {
            this.price *= rate;
            this.setCurrency(newCurrency);
        }
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency.toUpperCase();
    }
}
