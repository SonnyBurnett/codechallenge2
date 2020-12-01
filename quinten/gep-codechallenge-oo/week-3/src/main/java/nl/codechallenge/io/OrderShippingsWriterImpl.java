package nl.codechallenge.io;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import nl.codechallenge.model.CustomerInfo;
import nl.codechallenge.model.OrderShipping;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class OrderShippingsWriterImpl implements OrderShippingsWriter {

    @Override
    public void write(List<OrderShipping> orderShippings) throws WritingException, IOException {

        Path path = Paths.get("./outputs.csv");

        try (Writer writer = Files.newBufferedWriter(path)) {

            HeaderColumnNameMappingStrategy<OrderShipping> strategy = new HeaderColumnNameMappingStrategy<>();
            ignoreCountryField(strategy);
            strategy.setColumnOrderOnWrite(new OrderShippingColumnTitleComparator());

            StatefulBeanToCsv<OrderShipping> beanToCsv = new StatefulBeanToCsvBuilder<OrderShipping>(writer)
                    .withMappingStrategy(strategy)
                    .build();
            beanToCsv.write(orderShippings);
        } catch (NoSuchFieldException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new WritingException(e);
        }

        fixBugWithHeaderNames(path);
    }

    /**
     * Due to what I consider to be a bug:
     * https://stackoverflow.com/questions/56168094/why-opencsv-capitalizing-csv-headers-while-writing-to-file
     * the header titles are capitalized. This replaces the capitalized header with the right one. The
     * AnnotationStrategy approach (see link) would be way too complex due to the use of @CsvRecurse, which keeps our
     * beans DRY (which is more OO relate than this crap), and I thus want to keep. Not something to fix for this
     * exercise.
     */
    private void fixBugWithHeaderNames(Path path) throws IOException {
        String content = new String(Files.readAllBytes(path));
        content = content.replaceAll(
                "\"CUSTOMERID\",\"NAME\",\"SHIPPER\",\"DURATION\",\"SHIPPINGCOST\"",
                "\"CustomerId\",\"Name\",\"Shipper\",\"Duration\",\"ShippingCost\"");
        Files.writeString(path, content);
    }

    /**
     * Country cannot be ignored with an annotation, because it is defined once and should be read. Therefore, do some
     * hard work here to keep it from being written. Reflection for the win... (Java CSV libs are filthy that way)
     */
    private void ignoreCountryField(HeaderColumnNameMappingStrategy<OrderShipping> strategy) throws NoSuchFieldException {
        MultiValuedMap<Class<?>, Field> ignoredFields = new ArrayListValuedHashMap<>();
        ignoredFields.put(CustomerInfo.class, CustomerInfo.class.getDeclaredField("Country"));
        strategy.ignoreFields(ignoredFields);
        strategy.setType(OrderShipping.class);
    }

    static class OrderShippingColumnTitleComparator implements Comparator<String> {
        List<String> titles = Arrays.asList("CUSTOMERID", "NAME", "SHIPPER", "DURATION", "SHIPPINGCOST");
        @Override
        public int compare(String s1, String s2) {
            if (!titles.containsAll(Arrays.asList(s1, s2))) {
                throw new IllegalArgumentException("Invalid column title. Got: " + s1 + ", and " + s2  +
                        ". Expecting one of: " + titles);
            }
            return Integer.compare(titles.indexOf(s1), titles.indexOf(s2));
        }
    }
}
