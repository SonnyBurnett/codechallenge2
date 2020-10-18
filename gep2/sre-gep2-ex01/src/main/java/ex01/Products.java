package ex01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.*;

/**
 * Products, actually this class already names itself: It is the collection of products.
 * As we could feature more Products collections in the future it is named and stores the product properties as
 * a generic header, hiding the complexity of managing those.
 * Note: that this class needs to implement the write and load of the ProductsHandler interface.
 */

public class Products implements ProductsHandler {

    private final String name;
    private final List<Product> productsList = new LinkedList<>();
    private final List<String> propertyHeader = new LinkedList<>();

    public Products(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProduct(ProductDetail details) {
        productsList.add(new Product(details));
    }

    @SuppressWarnings("unused")
    public void removeProduct(ProductDetail details) {
        productsList.removeIf(target -> target.getDetails().matches(details));
    }

    public Product getProduct(String productProperty, String propertyValue) {
        for (Product product : productsList) {
            if (product.getProductProperty(productProperty).equals(propertyValue)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> search(ProductDetail searchDetail) {
        List<Product> matchingProducts = new LinkedList<>();
        for (Product product : productsList) {
            if (product.getDetails().matches(searchDetail))
                matchingProducts.add(product);
        }
        return matchingProducts;
    }

    @SuppressWarnings("unused")
    public List<Product> greaterThanOrEqual(ProductDetail searchDetail, String name) {
        List<Product> matchingProducts = new LinkedList<>();
        for (Product product : productsList) {
            if (product.getDetails().compareMonetary(searchDetail, name))
                matchingProducts.add(product);
        }
        return matchingProducts;
    }

    public List<Product> listAll() {
        return new LinkedList<>(productsList);
    }

    public void migrateValue(String name) {
        for (Product product : productsList) {
            String str = Currency.DOLLAR.toEuro(new BigDecimal(product.getDetails().getProperty("price")
                    .toString())).toString();
            product.getDetails().replaceProperty(name, str);
        }
    }

    @SuppressWarnings("unused")
    public void filterProducts(ProductDetail details) {
        productsList.removeIf(target -> !target.getDetails().matches(details));
    }

    public void filterGreaterThanOrEqual(ProductDetail details, String name) {
        productsList.removeIf(target -> !target.getDetails().compareMonetary(details, name));
    }

    @Override
    public boolean load(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));

            if (scanner.hasNextLine()) {
                String rawHeaderLine = scanner.nextLine();
                String[] headers = rawHeaderLine.split(",\\s*");
                for (String heading : headers) {
                    propertyHeader.add(heading.trim());
                }
            }

            while (scanner.hasNextLine()) {
                String rawValueLine = scanner.nextLine();
                String[] values = rawValueLine.split(",\\s*");
                ListIterator<String> listIterator = propertyHeader.listIterator();
                Map<String, String> properties = new HashMap<>();
                int i = 0;
                while (listIterator.hasNext()) {
                    properties.put(listIterator.next(), values[i++].trim());
                }
                this.addProduct(new ProductDetail(properties));
            }
            scanner.close();
        } catch (Exception error) {
            System.err.println("Read error from " + file);
            return false;
        }
        return true;
    }

    @Override
    public boolean write(String file) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));

            ListIterator<String> listIterator = propertyHeader.listIterator();
            while (listIterator.hasNext()) {
                out.write(listIterator.next() + (listIterator.hasNext() ? ", " : "\n"));
            }

            List<Product> allProducts = this.listAll();

            if (!allProducts.isEmpty()) {
                for (Product allProduct : allProducts) {
                    ProductDetail detail = allProduct.getDetails();

                    ListIterator<String> listIteratorP = propertyHeader.listIterator();
                    while (listIteratorP.hasNext()) {
                        out.write(detail.getProperty(listIteratorP.next()) + (listIteratorP.hasNext() ? ", " : "\n"));
                    }
                }
            }

            out.close();
        } catch (Exception error) {
            System.err.println("Write error to " + file);
            return false;
        }
        return true;
    }
}
