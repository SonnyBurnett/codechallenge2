package ex01;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The ProductDetail class is where the individual properties of the product are living.
 * We do not need to know how they are mapped and what their names are, but we need to be able to create, search,
 * compare, replace and retrieve (with getters). One specific BigDecimal compare method has been made to compare
 * very precise at decimal level (this is needed when using either money or other decimal dependant calculations).
 */

public class ProductDetail {

    private final Map<String, String> properties;

    public ProductDetail() {
        this.properties = new HashMap<>();
    }

    public ProductDetail(Map<String, String> properties) {
        if (properties == null) {
            this.properties = new HashMap<>();
        } else {
            this.properties = new HashMap<>(properties);
        }
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void replaceProperty(String propertyName, String propertyValue) {
        properties.replace(propertyName, propertyValue);
    }

    public boolean matches(ProductDetail otherSpec) {
        for (Object propertyObject : otherSpec.getProperties().keySet()) {
            String propertyName = (String) propertyObject;
            if (!properties.get(propertyName).equals(otherSpec.getProperty(propertyName))) {
                return false;
            }
        }
        return true;
    }

    public boolean compareMonetary(ProductDetail otherDetail, String propertyName) {
        for (Object propertyObject : otherDetail.getProperties().keySet()) {
            if (Objects.equals(propertyName, propertyObject)) {
                BigDecimal productDetailValue = new BigDecimal(properties.get(propertyName));
                BigDecimal otherDetailValue = new BigDecimal(otherDetail.getProperty(propertyName).toString());
                if (productDetailValue.compareTo(otherDetailValue) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
