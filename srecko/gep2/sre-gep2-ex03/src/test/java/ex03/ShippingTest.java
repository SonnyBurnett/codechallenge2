package ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShippingTest {
    private final Shipping testShipping = new Shipping();

    @BeforeEach
    void setUp() {
        this.testShipping.setFileName("gep2/sre-gep2-ex03/src/test/resources/NoFile.err");
    }

    @Test
    void test_shippingInfo() {
    }

    @Test
    void test_readFileException() {
        testShipping.setFileName("gep1/NoFile.err");
        Exception exception = assertThrows(Exception.class, testShipping::readFile);

        String expectedMessage = "Read error from gep1/NoFile.err";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void test_writeFileException() {
        testShipping.setFileName("gep1/NoFile.err");
        Exception exception = assertThrows(Exception.class, () -> testShipping.writeFile("gep1/NoFile.err"));

        String expectedMessage = "Write error to gep1/NoFile.err";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void test_setFileName() {
        String expectedMessage = "gep1/NoFile.err";
        testShipping.setFileName(expectedMessage);

        assertEquals(expectedMessage, testShipping.getFileName());
    }

    @Test
    void test_getFileName() {
        String expectedMessage = "gep2/sre-gep2-ex03/src/test/resources/NoFile.err";
        testShipping.setFileName(expectedMessage);

        assertEquals(expectedMessage, testShipping.getFileName());
    }
}