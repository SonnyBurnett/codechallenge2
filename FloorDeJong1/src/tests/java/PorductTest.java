import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PorductTest {

    @Test
    public void testCreate(){
        // assign
        long id = 1;
        String name = "Product1", description = "This is a product", category = "newProduct", currency = "Euro";
        double price = 1.20;

        // act
        Product productWithoutCurrency = new Product(id, name, description, price, category);
        Product productWithCurrency = new Product(id, name, description, price, category, currency);

        // assert
        assertEquals(id, productWithoutCurrency.getId());
        assertEquals(name, productWithoutCurrency.getName());
        assertEquals(description, productWithoutCurrency.getDescription());
        assertEquals(price, productWithoutCurrency.getPrice());
        assertEquals(category, productWithoutCurrency.getCategory());
        assertEquals("DOLLAR", productWithoutCurrency.getCurrency());

        assertEquals(id, productWithCurrency.getId());
        assertEquals(name, productWithCurrency.getName());
        assertEquals(description, productWithCurrency.getDescription());
        assertEquals(price, productWithCurrency.getPrice());
        assertEquals(category, productWithCurrency.getCategory());
        assertEquals(currency.toUpperCase(), productWithCurrency.getCurrency());
    }

    @Test
    public void testCheckPriceBelow() {
        // assign
        int priceLimit = 10;
        Product product1 = new Product(1, "a", "b", 5.0, "c");
        Product product2 = new Product(1, "a", "b", 10.0, "c");
        Product product3 = new Product(1, "a", "b", 12.0, "c");

        // act + assert
        assertTrue(product1.checkPriceBelow(priceLimit));
        assertFalse(product2.checkPriceBelow(priceLimit));
        assertFalse(product3.checkPriceBelow(priceLimit));
    }

    @Test
    public void testToString(){
        // assign
        long id = 1;
        String name = "Product1", description = "This is a product", category = "newProduct";
        double price = 1.20;
        String expectedResult = id + ", " + name + ", " + description + ", " + price + ", " + category;

        Product product = new Product(id, name, description, price, category);

        // act + assign
        assertEquals(expectedResult, product.toString());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        // assign
        Product product1 = new Product(1, "a", "b", 5.0, "c");

        // act
        Product product2 = product1.clone();

        // assert
        assertNotEquals(product1, product2);
    }

    @Test
    public void testCheckProduct() {
        // assign
        String correctInfo = "productId, name, description, price, category";
        String incorrectInfo = "productId, name, description, category";

        // act + assert
        assertTrue(Product.checkProductInfo(correctInfo));
        assertFalse(Product.checkProductInfo(incorrectInfo));
    }

    @Test
    public void testCompareTo() {
        // assign
        Product product1 = new Product(1, "a", "b", 5.0, "c");
        Product mockProduct = Mockito.mock(Product.class);
        Mockito.when(mockProduct.getPrice()).thenReturn(4.0).thenReturn(5.0).thenReturn(6.0);

        // act + assert
        assertTrue(product1.compareTo(mockProduct) > 0);
        assertEquals(0, product1.compareTo(mockProduct));
        assertTrue(product1.compareTo(mockProduct) < 0);
    }

    @Test
    public void testConverCurrencyDifferentCurrency() {
        // Assign
        Product product1 = new Product(1, "a", "b", 5.0, "c");

        // Act
        product1.convertCurrency("Euro", 0.85);

        // assert
        assertEquals("Euro".toUpperCase(), product1.getCurrency());
        assertEquals(5.0 * 0.85, product1.getPrice());

    }

    @Test
    public void testConverCurrencySameCurrency() {
        // Assign
        Product product1 = new Product(1, "a", "b", 5.0, "c");

        // Act
        product1.convertCurrency("Dollar", 0.85);

        // assert
        assertEquals("DOLLAR", product1.getCurrency());
        assertEquals(5.0, product1.getPrice());

    }
}
