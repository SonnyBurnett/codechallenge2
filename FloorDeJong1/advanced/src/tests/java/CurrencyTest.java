import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CurrencyTest {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(Currency.class);

    private static final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();

    @BeforeAll
    public static void setUp() {
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @AfterAll
    public static void cleanUp() {
        listAppender.stop();
    }

    @BeforeEach
    public void cleanUpEach() {
        listAppender.list.clear();
    }

    @Test
    public void testCreate(){
        // assign
        Double rate = Currency.usdRates.get(Currency.Type.EURO);

        // act
        Currency currencyNoType = new Currency();
        Currency productWithType = new Currency(Currency.Type.EURO);

        // assert
        assertEquals(Currency.Type.USD, currencyNoType.getCurrencyType());
        assertEquals(Currency.usdRates, currencyNoType.getConversionRates());

        assertEquals(Currency.Type.EURO, productWithType.getCurrencyType());
        for (Currency.Type type: Currency.usdRates.keySet()) {
            assertEquals(Currency.usdRates.get(type)/rate, productWithType.getRate(type));
        }
    }

    @Test
    public void testSetCurrencyTypeDifferentType() {
        // Assign
        Currency currency = new Currency();
        Double rate = Currency.usdRates.get(Currency.Type.EURO);

        // Act
        currency.setCurrencyType(Currency.Type.EURO);

        // Assert
        assertEquals(Currency.Type.EURO, currency.getCurrencyType());
        for (Currency.Type type: Currency.usdRates.keySet()) {
            assertEquals(Currency.usdRates.get(type) / rate, currency.getRate(type));
        }
    }

    @Test
    public void testSetCurrencyTypeSameType() {
        // Assign
        Currency currency = new Currency();

        // Act
        currency.setCurrencyType(Currency.Type.USD);

        // Assert
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(Level.WARN, logsList.get(0).getLevel());
        assertEquals("Currency unchanged, is already "+Currency.Type.USD, logsList.get(0).getMessage());
    }
}
