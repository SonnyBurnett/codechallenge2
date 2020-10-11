package local.test.util.csv;

import local.test.model.Category;
import local.test.model.Product;
import local.test.util.csv.CSV2ProductParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSV2ProductParserTest {

    private Product expected = new Product(1, "name", "desc", 42, Category.PANTS);

    @Test
    void getValidProductsFromCSVLine() {
        // test valid input lines, no matter how much spacing
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine("1,name,desc,42,pants"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine("1, name, desc, 42, pants"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine("   1   ,   name    ,    desc    ,      42    ,    pants    "));
    }
    @Test
    void parseInvalidInput(){
        Product expected = Product.invalid();
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(""));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(","));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(",,"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(",,,"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(",,,,"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine("42,,,,"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine("id,name,desc,1,pants"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine("42,name,desc,abc,pants"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine("42,name,desc,1,n/a category"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(",name,,,"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(",,descr,,"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(",,,42,"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(",,,price,"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(",,,,pants"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine(",,,,n/a category"));
        assertEquals(expected, CSV2ProductParser.getProductFromCSVLine("42,name,desc,10,na category"));
    }

    @Test
    void allLinesValidTest(){
        assertTrue(CSV2ProductParser.allLinesValid(new String[]{"a", "b"}));
        assertFalse(CSV2ProductParser.allLinesValid(new String[]{"", ""}));
        assertFalse(CSV2ProductParser.allLinesValid(new String[]{" ", " "}));
        assertFalse(CSV2ProductParser.allLinesValid(new String[]{"a", ""}));
        assertFalse(CSV2ProductParser.allLinesValid(new String[]{"", "b"}));
        assertFalse(CSV2ProductParser.allLinesValid(new String[]{"", ""}));
        assertFalse(CSV2ProductParser.allLinesValid(new String[]{null, ""}));
        assertFalse(CSV2ProductParser.allLinesValid(new String[]{"", null}));
        assertFalse(CSV2ProductParser.allLinesValid(new String[]{null, null}));
    }

    @Test
    void isLineValidTest(){
        assertFalse(CSV2ProductParser.isLineValid(null));
        assertFalse(CSV2ProductParser.isLineValid(""));
        assertFalse(CSV2ProductParser.isLineValid(" "));
        assertFalse(CSV2ProductParser.isLineValid("   "));
        assertTrue(CSV2ProductParser.isLineValid("yes, valid"));

    }
}