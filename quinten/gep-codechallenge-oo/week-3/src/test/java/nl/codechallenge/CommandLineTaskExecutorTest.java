package nl.codechallenge;

import nl.codechallenge.io.OrderShippingsWriter;
import nl.codechallenge.io.OrderedProductsReader;
import nl.codechallenge.service.ArgumentValidator;
import nl.codechallenge.service.OrderShippingsCalculator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

public class CommandLineTaskExecutorTest {

    private ArgumentValidator validator;
    private CommandLineTaskExecutor executor;
    private OrderedProductsReader orderedProductsReader;
    private OrderShippingsCalculator orderShippingsCalculator;
    private OrderShippingsWriter orderShippingsWriter;

    @BeforeEach
    void beforeEach() {
        validator = mock(ArgumentValidator.class);
        orderedProductsReader = mock(OrderedProductsReader.class);
        orderShippingsCalculator = mock(OrderShippingsCalculator.class);
        orderShippingsWriter = mock(OrderShippingsWriter.class);
        executor = new CommandLineTaskExecutor(validator, orderedProductsReader, orderShippingsCalculator,
                orderShippingsWriter);
    }

    @Test
    void runHappyFlow() throws IOException {
//        // GIVEN
//        List<OrderedProduct> orderedProducts = new ArrayList<>();
//        orderedProducts.add(new OrderedProduct(1, "Piet", "Ding", 2.0, 3.5, Country.France));
//        when(orderedProductsReader.read(any(Path.class))).thenReturn(orderedProducts);
//
//        // WHEN
//        executor.run("src/test/resources/inputs.csv", "extraArg");
//
//        // THEN
//        assertValidation(validator);
//        verify(orderWeightCalculator, times(1)).calc(Arrays.asList(
//                new OrderedProduct(1, "Piet", "Ding", 2.0, 3.5, Country.France)
//        ));
    }

    private void assertValidation(ArgumentValidator validator) {
        ArgumentCaptor<List<String>> args = ArgumentCaptor.forClass(List.class);
        verify(validator, times(1)).validate(args.capture());
        List<String> validationArgs = args.getValue();
        assertThat(validationArgs, hasSize(2));
        assertThat(validationArgs.get(0), Matchers.equalTo("src/test/resources/inputs.csv"));
        assertThat(validationArgs.get(1), Matchers.equalTo("extraArg"));
    }
}
