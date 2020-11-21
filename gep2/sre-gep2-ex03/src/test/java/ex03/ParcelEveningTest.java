package ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ParcelEveningTest {
    private Shipping testShipping;

    @BeforeEach
    void setUp() {
        testShipping = new Shipping();
    }

    @Test
    void test_run() {
        String testPathStr = "src/test/resources/";
        String testInfile = "003-experts-example.csv";
        String testOutfile = "test.csv";

        File testFile = new File(testPathStr + testInfile);
        if (!testFile.exists()) {
            testPathStr = "gep2/sre-gep2-ex03/src/test/resources/";
        }
        /* TODO: fix IntelliJ, JUnit and Maven resources path conflicts for read resource paths. */

        testShipping.shippingInfo(testInfile, testOutfile, testPathStr);

        String actualLines = getFileString(testPathStr + testOutfile);

        String expectedLines = "CustomerId, Name, Shipper, Duration, ShippingCost\n" +
                "16, Henry Been, PostNL, 1, 6.95\n" +
                "21, Pietje de Boer, BelgioPost, 2, 4.45\n";

        assertEquals(expectedLines, actualLines);
    }



    @Test
    void test_actual() {
        String testPathStr = "src/test/resources/";
        String testInfile = "003-experts-inputs.csv";
        String testOutfile = "output.csv";

        File testFile = new File(testPathStr + testInfile);
        if (!testFile.exists()) {
            testPathStr = "gep2/sre-gep2-ex03/src/test/resources/";
        }
        /* TODO: fix IntelliJ, JUnit and Maven resources path conflicts for read resource paths. */

        testShipping.shippingInfo(testInfile, testOutfile, testPathStr);

        String actualLines = getFileString(testPathStr + testOutfile);

        String expectedLines = "CustomerId, Name, Shipper, Duration, ShippingCost\n" +
                "16, Henry Been, PostNL, 1, 6.95\n" +
                "21, Pietje de Boer, BelgioPost, 2, 4.95\n" +
                "8, Kareltje van Gruttenveen, DHL, 4, 15.2\n";

        assertEquals(expectedLines, actualLines);
    }

    private String getFileString(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder actualLines = new StringBuilder();
        while (Objects.requireNonNull(scanner).hasNextLine()) {
            actualLines.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return actualLines.toString();
    }
}