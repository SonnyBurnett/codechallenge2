package nl.codechallenge.io;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderShippingsWriterImplTest {
    @Test
    void titleOrdering() {
        List<String> given = Arrays.asList("SHIPPER", "CUSTOMERID", "SHIPPINGCOST", "DURATION", "NAME");
        List<String> expected = Arrays.asList("CUSTOMERID", "NAME", "SHIPPER", "DURATION", "SHIPPINGCOST");

        Comparator<String> comparator = new OrderShippingsWriterImpl.OrderShippingColumnTitleComparator();

        // when (modifying 'given', which is ugly but works fine enough for the test
        given.sort(comparator);
        // then
        assertThat(given).isEqualTo(expected);
    }
}
