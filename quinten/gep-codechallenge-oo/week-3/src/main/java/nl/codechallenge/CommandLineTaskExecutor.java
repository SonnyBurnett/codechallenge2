package nl.codechallenge;

import nl.codechallenge.io.OrderShippingsWriter;
import nl.codechallenge.io.OrderedProductsReader;
import nl.codechallenge.model.OrderShipping;
import nl.codechallenge.model.OrderedProduct;
import nl.codechallenge.service.ArgumentValidator;
import nl.codechallenge.service.ShippingCalculator;
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
    private final ShippingCalculator shippingCalculator;
    private final OrderShippingsWriter orderShippingsWriter;

    @Autowired
    public CommandLineTaskExecutor(ArgumentValidator argumentValidator, OrderedProductsReader orderedProductsReader,
                                   ShippingCalculator shippingCalculator, OrderShippingsWriter orderShippingsWriter) {
        this.argumentValidator = argumentValidator;
        this.orderedProductsReader = orderedProductsReader;
        this.shippingCalculator = shippingCalculator;
        this.orderShippingsWriter = orderShippingsWriter;
    }

    @Override
    public void run(String... args) throws IOException {
        argumentValidator.validate(Arrays.asList(args));
        List<OrderedProduct> orderedProducts = orderedProductsReader.read(Paths.get(args[0]));
        List<OrderShipping> orderShippings = shippingCalculator.calc(orderedProducts);
        orderShippingsWriter.write(orderShippings);
    }

}
