package ex01;

/**
 * The Product class is the body part of the products, due to the simplicity of the OOP design it sits
 * between the Products and ProductDetail classes, stitching them together.
 * This is the best described as tying the the world of list products with the world of the properties.
 */

public class Product {

    ProductDetail details;

    @SuppressWarnings("unused")
    public Product() {
        this.details = new ProductDetail();
    }

    public Product(ProductDetail details) {
        this.details = details;
    }

    public String getProductProperty(String propertyName) {
        return this.details.getProperty(propertyName).toString();
    }

    public ProductDetail getDetails() {
        return details;
    }
}
