package nl.codechallenge.io;

import nl.codechallenge.model.OrderShipping;

import java.util.List;

public interface OrderShippingsWriter {
    void write(List<OrderShipping> orderShippings);
}
