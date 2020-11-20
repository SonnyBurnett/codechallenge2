package io;

import model.customer.Customer;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ShippingInfoWriterTest {

    private Customer customer = mock(Customer.class);

    private FileWriterFactory writerFactory = mock(FileWriterFactory.class);

    private ShippingInfoWriter infoWriter = new ShippingInfoWriter(writerFactory);

    @Test
    public void test_write() throws IOException {
        String fileName = "FloorDeJong3/src/tests/resources/output.csv";
        FileWriter fileWriter = spy(new FileWriter(fileName));
        when(writerFactory.create(anyString())).thenReturn(fileWriter);

        int size = 4;
        Map<Long, Customer> database = new HashMap<>();
        for (long i=0; i < size; i++) {
            database.put(i, customer);
        }

        String info = "1,Name,Shipper,Duration,Costs";
        when(customer.getShippingInfo()).thenReturn(info);

        infoWriter.write(fileName, database);

        verify(fileWriter, times(size)).write(info);
    }
}
