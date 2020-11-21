package ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackagesTest {
    private Packages testSetPackages = new Packages();

    @BeforeEach
    void setUp() {
        Package aPackage = new Package("PostNL",
                1,
                6.95,
                0.00);
        testSetPackages.addPackage(aPackage);
    }

    @Test
    void test_addPackage() {
        Package aPackage = new Package("Test",
                -999,
                -999.99,
                -999.999);
        testSetPackages.addPackage(aPackage);

        String expectedMessage = "\n" +
                "2\n" +
                "PostNL, 1, 6.95, 0.0\n" +
                "Test, -999, -999.99, -999.999\n";

        assertEquals(expectedMessage, testSetPackages.toString());
    }

    @Test
    void test_toString() {
        String expectedMessage = "\n" +
                "1\n" +
                "PostNL, 1, 6.95, 0.0\n";

        assertEquals(expectedMessage, testSetPackages.toString());
    }

    @Test
    void test_toPrint() {
        String expectedMessage = "\n" +
                "1\n" +
                "PostNL, 1, 6.95, 0.0\n";

        assertEquals(expectedMessage, testSetPackages.toString());
    }

    @Test
    void test_size() {
        assertEquals(1, testSetPackages.size());
    }
}