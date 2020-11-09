package domain;

/**
 * Methods to be implemented by the Product class.
 */
public interface IProduct {

    public abstract void newProduct(
            String name,
            String id,
            String description,
            String category,
            double price );

    public abstract double getPrice();
    public abstract String getName();
    public abstract String getId();
    public abstract String getDescription();
    public abstract String getCategory();


}
