import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestProductFilter {

    private final ProductFilter filter = new ProductFilter();

    private final Product mockProduct = mock(Product.class);

    private final List<Product> productList = new ArrayList<>();
    {productList.add(mockProduct);}

    @Test
    public void testReadProductFileNotExistingFile(){
        // assign
        String fileName = "thisIsAnNonExistingFile.text";

        // act + assert
        assertThrows(FileNotFoundException.class, () -> filter.readProductFile(fileName) );
    }

    @Test
    public void testReadProductFileCorrectProductFile() throws FileNotFoundException {
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
    public void testReadProductFileIncorrectProductFile() throws FileNotFoundException {
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
    public void testCloneList() throws CloneNotSupportedException {
        // Assign
        List<Product> newProductList = new ArrayList<>(productList);
        newProductList.add(mockProduct);

        // Act
        List<Product> newList = filter.cloneList(newProductList);

        // assert
        assertEquals(2, newList.size());
        verify(mockProduct, times(2)).clone();
    }
}
