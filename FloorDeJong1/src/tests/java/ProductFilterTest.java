import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.CloneNotSupportedException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class ProductFilterTest {

    private final ProductFilter filter = new ProductFilter();

    private static final Product mockProduct = mock(Product.class);

    private static final List<Product> productList = new ArrayList<>();

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductFilter.class);

    private static final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();

    @BeforeAll
    public static void setUp() {
        productList.add(mockProduct);

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
        reset(mockProduct);
    }

    @Test
    public void testReadProductFileNotExistingFile() {
        // Assign
        String fileName = "thisIsAnNonExistingFile.text";

        // Act
        filter.readProductFile(fileName);

        // Assert
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(Level.ERROR, logsList.get(0).getLevel());
        assertTrue(logsList.get(0).getMessage().contains("FileNotFoundException"));

    }

    @Test
    public void testReadProductFileCorrectProductFile() {
        // assign
        String fileName = "FloordeJong1/src/tests/resources/correctInput.csv";

        // act
        filter.readProductFile(fileName);

        // assert
        assertEquals(1, filter.getInputList().size());
        Product product = filter.getInputList().get(0);
        assertEquals(1, product.getId());
        assertEquals("shorts", product.getName());
        assertEquals("short pants", product.getDescription());
        assertEquals(8, product.getPrice());
        assertEquals("pants", product.getCategory());
        assertEquals("DOLLAR", product.getCurrency());
    }

    @Test
    public void testReadProductFileIncorrectProductFile() {
        // assign
        String fileName = "FloordeJong1/src/tests/resources/incorrectInput.csv";

        // act
        filter.readProductFile(fileName);

        // assert
        assertTrue(filter.getInputList().isEmpty());
    }



    @Test
    public void testFilterOutPriceBelow(){
        //assign
        int priceLimit = 10;
        when(mockProduct.checkPriceBelow(priceLimit)).thenReturn(true);

        // Act
        List<Product> filterList = filter.filterOut(productList, p -> p.checkPriceBelow(priceLimit));

        // Assert
        assertTrue(filterList.isEmpty());
    }

    @Test
    public void testFilterOutPriceAbove(){
        //assign
        int priceLimit = 10;
        when(mockProduct.checkPriceBelow(priceLimit)).thenReturn(false);

        // Act
        List<Product> filterList = filter.filterOut(productList, p -> p.checkPriceBelow(priceLimit));

        // Assert
        assertFalse(filterList.isEmpty());
        assertEquals(1, filterList.size());
    }

    @Test
    public void testConvertCurrency() throws CloneNotSupportedException {
        // assign
        when(mockProduct.clone()).thenReturn(mockProduct);

        List<Product> newProductList = new ArrayList<>(productList);
        newProductList.add(mockProduct);
        String newCurrency = "euro";
        double rate = 0.85;

        // Act
        List<Product> convertedList = filter.convertCurrency(newProductList, newCurrency, rate);

        // assert
        assertEquals(2, convertedList.size());
        verify(mockProduct, times(2)).clone();
        verify(mockProduct, times(2)).convertCurrency(newCurrency, rate);
    }

    @Test
    public void testWriteProductFile() {
        // Assign
        String fileName = System.getProperty("java.io.tmpdir") + "/output.csv";
        when(mockProduct.toString()).thenReturn("1, a, b, 0.85, c");

        // Act
        filter.writeProductFile(productList , fileName);

        // Assert
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(Level.INFO, logsList.get(0).getLevel());
        assertTrue(logsList.get(0).getMessage().contains("Successfully wrote to file:"));

        assertTrue(checkFileExists(fileName));
    }

    @Test
    public void testCloneListCloneable() throws CloneNotSupportedException {
        // Assign
        List<Product> newProductList = new ArrayList<>(productList);
        newProductList.add(mockProduct);

        // Act
        List<Product> newList = filter.cloneList(newProductList);

        // assert
        assertEquals(2, newList.size());
        verify(mockProduct, times(2)).clone();
    }

    @Test
    public void testCloneListNotCloneable() throws CloneNotSupportedException {
        // Assign
        List<Product> newProductList = new ArrayList<>(productList);
        when(mockProduct.clone()).thenThrow(new CloneNotSupportedException());

        // Act
        List<Product> newList = filter.cloneList(newProductList);

        // Assert
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(Level.ERROR, logsList.get(0).getLevel());
        assertTrue(logsList.get(0).getMessage().contains("CloneNotSupportedException"));

        assertEquals(0, newList.size());
        verify(mockProduct, times(1)).clone();
    }

    private boolean checkFileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
