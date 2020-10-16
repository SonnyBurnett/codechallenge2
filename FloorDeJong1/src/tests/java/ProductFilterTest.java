import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductFilterTest {

    private final ProductFilter filter = new ProductFilter();

    @Mock
    private final Product mockProduct = Mockito.mock(Product.class);

    private final List<Product> productList = new ArrayList<>();
    { productList.add(mockProduct);}

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
        Mockito.when(mockProduct.checkPriceBelow(priceLimit)).thenReturn(true);

        // Act
        List<Product> filterList = filter.filterOut(productList, p -> p.checkPriceBelow(priceLimit));

        // Assert
        assertTrue(filterList.isEmpty());
    }

    @Test
    public void testFilterOutPriceAbove(){
        //assign
        int priceLimit = 10;
        Mockito.when(mockProduct.checkPriceBelow(priceLimit)).thenReturn(false);

        // Act
        List<Product> filterList = filter.filterOut(productList, p -> p.checkPriceBelow(priceLimit));

        // Assert
        assertFalse(filterList.isEmpty());
        assertEquals(1, filterList.size());
    }

//    @Test
//    public void testConvertCurrency() throws CloneNotSupportedException {
//        Product mockProduct = Mockito.mock(Product.class);
////        Mockito.when(mockProduct.setCurrency()).thenReturn(false);
//
//        List<Product> productList = new ArrayList<>();
//        productList.add(mockProduct);
//
//
//        filter.convertCurrency(productList, "euro", 0.85);
//    }


    @Test
    public void testCloneList() throws CloneNotSupportedException {
        // Act
        List<Product> newList = filter.cloneList(productList);

        // assign
        assertNotEquals(productList, newList);
    }
}
