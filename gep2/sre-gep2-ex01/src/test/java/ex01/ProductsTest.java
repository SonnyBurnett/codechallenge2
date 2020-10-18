package ex01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ProductsTest {

    private final Products test_Products = new Products("Test Products");
//    private static ProductDetail test_Details;

    @BeforeEach
    void test_setUp() {
        Map<String, String> test_properties = new HashMap<>();

        test_properties.put("productId", "45848");
        test_properties.put("name", "shorts");
        test_properties.put("description", "short pants");
        test_properties.put("price", "8");
        test_properties.put("category", "pants");

        test_Products.addProduct(new ProductDetail(test_properties));

        assertEquals("45848",
                test_Products.getProduct("productId", "45848"
                ).getDetails().getProperty("productId"));

        test_properties.put("productId", "4184688");
        test_properties.put("name", "trousers");
        test_properties.put("description", "trousers");
        test_properties.put("price", "12");
        test_properties.put("category", "pants");

        test_Products.addProduct(new ProductDetail(test_properties));

        assertEquals("4184688", test_Products.getProduct("productId", "4184688"
        ).getDetails().getProperty("productId"));

        test_properties.put("productId", "848488");
        test_properties.put("name", "blue shirt");
        test_properties.put("description", "shirt");
        test_properties.put("price", "88");
        test_properties.put("category", "shirts");

        test_Products.addProduct(new ProductDetail(test_properties));

        assertEquals("848488",
                test_Products.getProduct("productId", "848488"
                ).getDetails().getProperty("productId"));


    }

    @AfterEach
    void test_tearDown() {
    }

    @Test
    void test_getName() {
        assertEquals("Test Products", test_Products.getName());
    }

    @Test
    void test_addProduct() {
        Map<String, String> test_properties = new HashMap<>();

        test_properties.put("productId", "test product identification");
        test_properties.put("name", "test product name");
        test_properties.put("description", "test product description");
        test_properties.put("price", "0.00");
        test_properties.put("category", "test product category");

        test_Products.addProduct(new ProductDetail(test_properties));

        assertEquals("test product identification",
                test_Products.getProduct("productId", "test product identification"
                ).getDetails().getProperty("productId"));
        assertEquals("test product name",
                test_Products.getProduct("productId", "test product identification"
                ).getDetails().getProperty("name"));
        assertEquals("test product description",
                test_Products.getProduct("productId", "test product identification"
                ).getDetails().getProperty("description"));
        assertEquals("0.00",
                test_Products.getProduct("productId", "test product identification"
                ).getDetails().getProperty("price"));
        assertEquals("test product category",
                test_Products.getProduct("productId", "test product identification"
                ).getDetails().getProperty("category"));
    }

    @Test
    void test_removeProduct() {
        test_addProduct();

        Map<String, String> test_properties = new HashMap<>();

        test_properties.put("productId", "test product identification");
        test_properties.put("name", "test product name");
        test_properties.put("description", "test product description");
        test_properties.put("price", "0.00");
        test_properties.put("category", "test product category");

        test_Products.removeProduct(new ProductDetail(test_properties));

        assertNull(
                test_Products.getProduct("productId", "test product identification"
                ));
    }

    @Test
    void test_getProduct() {
        test_addProduct();
        assertEquals("test product identification",
                test_Products.getProduct("productId", "test product identification"
                ).getDetails().getProperty("productId"));

        test_removeProduct();
        assertNull(
                test_Products.getProduct("productId", "test product identification"
                ));
    }

    @Test
    void test_search() {
        Map<String, String> properties = new HashMap<>();
        properties.put("price", "12");
        properties.put("category", "pants");
        ProductDetail whatToSearch = new ProductDetail(properties);

        List<Product> matchingProducts = test_Products.search(whatToSearch);
        StringBuilder test_String = new StringBuilder();

        if (!matchingProducts.isEmpty()) {
            test_String.append("---\nMatching products:\n");
            for (ex01.Product Product : matchingProducts) {
                ProductDetail detail = Product.getDetails();
                test_String.append("Found match ").append(detail.getProperty("productId"))
                        .append("\n with the following properties:\n");
                for (String propertyName : detail.getProperties().keySet()) {
                    if (propertyName.equals("category"))
                        continue;
                    test_String.append("    ").append(propertyName).append(": ")
                            .append(detail.getProperty(propertyName)).append("\n");
                }
            }
            test_String.append("---\n");
        }

        String test_expectedString =
                "---\n" +
                        "Matching products:\n" +
                        "Found match 4184688\n" +
                        " with the following properties:\n" +
                        "    name: trousers\n" +
                        "    description: trousers\n" +
                        "    productId: 4184688\n" +
                        "    price: 12\n" +
                        "---\n";

        System.out.println(test_String.toString());
        assertEquals(test_expectedString, test_String.toString());
    }

    @Test
    void test_greaterThanOrEqual() {
    }

    @Test
    void test_listAll() {
    }

    @Test
    void test_migrateValue() {
    }

    @Test
    void test_filterProducts() {
    }

    @Test
    void test_filterGreaterThanOrEqual() {
    }

    @Test
    void test_load() {

        /* Yes inline comments means something nasty;
         * IntelliJ, JUnit and Maven modular modules have a testing resources issue this is the workaround for now. */
        String testFileStr = "src/test/resources/001-test-inputs.csv";
        File testFile = new File(testFileStr);
        if (!testFile.exists()) {
            testFileStr = "gep2/sre-gep2-ex01/src/test/resources/001-test-inputs.csv";
        }
        /* TODO: fix IntelliJ, JUnit and Maven resources path conflicts for read resource paths. */

        assertTrue(test_Products.load(testFileStr));
    }

    @Test
    void test_write() {
        test_load();

        /* Yes inline comments means something nasty;
         * IntelliJ, JUnit and Maven modular modules have a testing resources issue this is the workaround for now. */
        String testFileStr = "src/test/resources/001-test-inputs.csv";
        File testFile = new File(testFileStr);
        if (!testFile.exists()) {
            testFileStr = "gep2/sre-gep2-ex01/src/test/resources/001-test-outputs.csv";
        } else {
            testFileStr = "src/test/resources/001-test-outputs.csv";
        }
        /* TODO: fix IntelliJ, JUnit and Maven resources path conflicts for write resource paths. */

        assertTrue(test_Products.write(testFileStr));
    }
}