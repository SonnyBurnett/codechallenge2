package ex03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PackageTest {
    private Package testPackage = new Package();

    @Test
    void getTotalWeight() {
        testPackage.setTotalWeight(-999.999);

        assertEquals(-999.999, testPackage.getTotalWeight());
    }

    @Test
    void setTotalWeight() {
        testPackage.setTotalWeight(-999.999);

        assertEquals(-999.999, testPackage.getTotalWeight());
    }

    @Test
    void getShipper() {
        testPackage.setShipper("Sint Nicolas");

        assertEquals("Sint Nicolas", testPackage.getShipper());
    }

    @Test
    void setShipper() {
        testPackage.setShipper("Sint Nicolas");

        assertEquals("Sint Nicolas", testPackage.getShipper());
    }

    @Test
    void getDuration() {
        testPackage.setDuration(13);

        assertEquals(13, testPackage.getDuration());
    }

    @Test
    void setDuration() {
        testPackage.setDuration(13);

        assertEquals(13, testPackage.getDuration());
    }

    @Test
    void getShippingCost() {
        assertEquals(0.00, testPackage.getShippingCost());
    }

    @Test
    void setShippingCost() {
        testPackage.setShippingCost(12.05);

        assertEquals(12.05, testPackage.getShippingCost());
    }
}