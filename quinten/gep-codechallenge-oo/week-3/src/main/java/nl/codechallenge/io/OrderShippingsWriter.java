package nl.codechallenge.io;

import nl.codechallenge.model.OrderShipping;

import java.io.IOException;
import java.util.List;

public interface OrderShippingsWriter {
    void write(List<OrderShipping> orderShippings) throws WritingException, IOException;
}
