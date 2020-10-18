import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;

public class ProductTest {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(Product.class);

    private static final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();

    @BeforeAll
    public static void setUp() {
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @AfterAll
    public static void cleanUp() {
        listAppender.stop();
    }

    @BeforeEach
    public void cleanUpEach() {
        listAppender.list.clear();
    }


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
    public void testCheckProductCorrect() {
        // assign
        String correctInfo = "productId, name, description, price, category";

        // act + assert
        assertTrue(Product.checkProductInfo(correctInfo));
    }

    @Test
    public void testCheckProductIncorrect() {
        // assign
        String incorrectInfo = "productId, name, description, category";

        // act + act
        assertFalse(Product.checkProductInfo(incorrectInfo));

        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(Level.ERROR, logsList.get(0).getLevel());
        assertTrue(logsList.get(0).getMessage().contains("Product information is incorrect."));
    }

    @Test
    public void testConvertCurrencyDifferentCurrency() {
        // Assign
        Product product1 = new Product(1, "a", "b", 5.0, "c");

        // Act
        product1.convertCurrency("Euro", 0.85);

        // assert
        assertEquals("Euro".toUpperCase(), product1.getCurrency());
        assertEquals(5.0 * 0.85, product1.getPrice());

    }

    @Test
    public void testConvertCurrencySameCurrency() {
        // Assign
        Product product1 = new Product(1, "a", "b", 5.0, "c");

        // Act
        product1.convertCurrency("Dollar", 0.85);

        // assert
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(Level.INFO, logsList.get(0).getLevel());
        assertTrue(logsList.get(0).getMessage().contains("Price of product is already in "));

        assertEquals("DOLLAR", product1.getCurrency());
        assertEquals(5.0, product1.getPrice());



    }
}
