import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Product implements Cloneable {
    private long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private Currency currency;

    public static final String PRODUCT_INFO = "productId, name, description, price, category";
    static final Logger LOGGER = LoggerFactory.getLogger(Product .class);

    public Product(long id, String name, String description, double price, String category, Currency.Type currency){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.currency = new Currency(currency);
    }

    public Product(long id, String name, String description, double price, String category){
        this(id, name, description, price, category, Currency.Type.USD);
    }

    public boolean checkPriceBelow(double max) {
        return this.price < max;
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
        LOGGER.error("Product information is incorrect.\n" + Arrays.toString(lineList));
        return false;
    }

    public void convertCurrency(Currency.Type newCurrency) {
        if (!this.currency.getCurrencyType().equals(newCurrency)) {
            this.price *= this.currency.getRate(newCurrency);
            this.currency.setCurrencyType(newCurrency);
        } else {
            LOGGER.info("Price of product is already in " + newCurrency);
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

    public Currency getCurrency() {
        return currency;
    }
}
 
