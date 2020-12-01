package nl.codechallenge;

import nl.codechallenge.io.OrderShippingsWriter;
import nl.codechallenge.io.OrderedProductsReader;
import nl.codechallenge.io.WritingException;
import nl.codechallenge.model.OrderShipping;
import nl.codechallenge.model.OrderedProduct;
import nl.codechallenge.service.ArgumentValidator;
import nl.codechallenge.service.OrderShippingsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Profile("!test")
@Component
public class CommandLineTaskExecutor implements CommandLineRunner {

    private final ArgumentValidator argumentValidator;
    private final OrderedProductsReader orderedProductsReader;
    private final OrderShippingsCalculator orderShippingsCalculator;
    private final OrderShippingsWriter orderShippingsWriter;

    @Autowired
    public CommandLineTaskExecutor(ArgumentValidator argumentValidator, OrderedProductsReader orderedProductsReader,
                                   OrderShippingsCalculator orderShippingsCalculator, OrderShippingsWriter orderShippingsWriter) {
        this.argumentValidator = argumentValidator;
        this.orderedProductsReader = orderedProductsReader;
        this.orderShippingsCalculator = orderShippingsCalculator;
        this.orderShippingsWriter = orderShippingsWriter;
    }

    @Override
    public void run(String... args) throws IOException, WritingException {
        argumentValidator.validate(Arrays.asList(args));
        List<OrderedProduct> orderedProducts = orderedProductsReader.read(Paths.get(args[0]));
        List<OrderShipping> orderShippings = orderShippingsCalculator.calc(orderedProducts);
        orderShippingsWriter.write(orderShippings);
    }

}
