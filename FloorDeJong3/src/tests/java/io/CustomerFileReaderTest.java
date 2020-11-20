package io;

import model.customer.Customer;
import model.customer.CustomerFactory;
import model.product.Product;
import model.product.ProductFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerFileReaderTest {

    private Product product = mock(Product.class);

    private ProductFactory productFactory = mock(ProductFactory.class);

    private Customer customer = mock(Customer.class);

    private CustomerFactory customerFactory = mock(CustomerFactory.class);

    private CustomerFileReader reader = spy(new CustomerFileReader(productFactory,customerFactory));

    private Map<Long, Customer> database = mock(HashMap.class);

    {
        when(productFactory.create(anyString(), anyDouble(), anyDouble())).thenReturn(product);
        when(customerFactory.create(anyLong(), anyString(), anyString())).thenReturn(customer);
    }

    @Test
    public void test_checkField_correctInput() {
        assertEquals(0, reader.checkFieldData("a", "long", "123"));
        assertEquals(0, reader.checkFieldData("a", "double", "123.45"));
    }

    @Test
    public void test_checkField_incorrectInput() {
        // Check logging
        assertEquals(1, reader.checkFieldData("a", "long", "123.45"));
        assertEquals(1, reader.checkFieldData("a", "long", "b"));
        assertEquals(1, reader.checkFieldData("a", "double", "c"));
    }

    @Test
    public void test_checkData_correct() {
        doReturn(0).when(reader).checkFieldData(anyString(), anyString(), anyString());

        assertTrue(reader.checkData(new String[]{"1", "name", "product", "1.23", "4.5", "country"}));
    }

    // ToDo: check logging
    @Test
    public void test_checkData_incorrect() {
        // To many fields
        assertFalse(reader.checkData(new String[]{"a", "b", "c", "d", "e", "f", "g"}));

        // Incorrect customerId
        doReturn(1, 0, 0).when(reader).checkFieldData(anyString(), anyString(), anyString());
        assertFalse(reader.checkData(new String[]{"a", "b", "c", "d", "e", "f"}));

        // Incorrect price
        doReturn(0, 1, 0).when(reader).checkFieldData(anyString(), anyString(), anyString());
        assertFalse(reader.checkData(new String[]{"a", "b", "c", "d", "e", "f"}));

        // Incorrect weight
        doReturn(0, 0, 1).when(reader).checkFieldData(anyString(), anyString(), anyString());
        assertFalse(reader.checkData(new String[]{"a", "b", "c", "d", "e", "f"}));
    }

    @Test
    public void test_checkFieldName() {
        assertEquals(0, reader.checkFieldName("a", "a"));
        assertEquals(0, reader.checkFieldName("a", "A"));

        // Check log message
        assertEquals(1, reader.checkFieldName("a", "b"));
    }

    @Test
    public void test_checkHeaderInfo_correct() {
        doReturn(0).when(reader).checkFieldName(anyString(),anyString());
        assertTrue(reader.checkHeaderInfo("a,b,c,d,e,f"));
    }

    // ToDo: check logging message
    @Test
    public void test_checkHeaderInfo_incorrect() {
        // To many fields
        assertFalse(reader.checkHeaderInfo("a,b,c,d,e,f,g"));

        // One incorrect header name
        doReturn(0, 1, 0).when(reader).checkFieldName(anyString(),anyString());
        assertFalse(reader.checkHeaderInfo("a,b,c,d,e,f"));

        // Multiple incorrect header name
        doReturn(0, 1, 1, 1, 0).when(reader).checkFieldName(anyString(),anyString());
        assertFalse(reader.checkHeaderInfo("a,b,c,d,e,f"));
    }

    @Test
    public void test_processData_existingCustomer() {
        when(database.get(anyLong())).thenReturn(customer);
        String line = "1,name,product,2,3,country";

        reader.processDate(database, line);

        verify(database).get(1L);
        verify(customer).addProduct(product);
        verify(customerFactory, times(0)).create(anyLong(), anyString(), anyString());
        verify(database, times(0)).put(anyLong(),any());
    }

    @Test
    public void test_processData_newCustomer() {
        when(database.get(anyLong())).thenReturn(null);
        Long id = 1L;

        reader.processDate(database, id + ",name,product,2,3,country");

        verify(database).get(id);
        verify(customerFactory).create(anyLong(), anyString(), anyString());
        verify(customer).addProduct(product);
        verify(database).put(id, customer);
    }

    @Test
    public void test_read_correctHeader() throws IOException {
        Map<Long,Customer> customerDatabase = spy(new HashMap<>());
        String fileName = "FloorDeJong3/src/tests/resources/correctInput.csv";

        reader.read(fileName, customerDatabase);

        verify(reader, times(1)).checkHeaderInfo(anyString());
        verify(reader, times(6)).processDate(anyMap(),anyString());
        verify(reader, times(6)).checkData(any());
        verify(customerDatabase, times(2)).get(anyLong());
    }

    @Test
    public void test_read_toManyHeaders() throws IOException {
        Map<Long,Customer> customerDatabase = spy(new HashMap<>());
        String fileName = "FloorDeJong3/src/tests/resources/ToManyHeadderFields.csv";

        reader.read(fileName, customerDatabase);

        verify(reader, times(1)).checkHeaderInfo(anyString());
        verify(reader, times(0)).processDate(anyMap(),anyString());
    }

    @Test
    public void test_read_wrongFieldName() throws IOException {
        Map<Long,Customer> customerDatabase = spy(new HashMap<>());
        String fileName = "FloorDeJong3/src/tests/resources/WrongHeaderField.csv";

        reader.read(fileName, customerDatabase);

        verify(reader, times(1)).checkHeaderInfo(anyString());
        verify(reader, times(0)).processDate(anyMap(),anyString());
    }

}
