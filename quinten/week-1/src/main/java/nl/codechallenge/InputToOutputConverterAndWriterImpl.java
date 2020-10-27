package nl.codechallenge;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

@Service
class InputToOutputConverterAndWriterImpl implements InputToOutputConverterAndWriter {

    @Override
    public void createOutputs(String absoluteFilePath)
            throws IllegalStateException, FileNotFoundException, IOException {

        System.out.println("Converting inputs to outputs");

        try (FileReader reader = new FileReader(absoluteFilePath);
                CSVReader csvReader = new CSVReader(reader);
                Writer writer = Files.newBufferedWriter(Paths.get("./outputs.csv"));
                CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {

            Iterator<String[]> it = csvReader.iterator();

            if (!it.hasNext()) {
                throw new RuntimeException("Input file does not have a first line");
            }

            String[] header = it.next();
            assertCorrectHeaderLine(header);

            csvWriter.writeNext(header);

            while (it.hasNext()) {
                Product p = parseCsvRowToProduct(it.next());
                if (p.isTenPlusDollars()) {
                    csvWriter.writeNext(productToEuroRow(p));
                }
            }

        }

    }

    static void assertCorrectHeaderLine(String[] header) {
        String[] expectedHeader = { "productId", "name", "description", "price", "category" };

        if (!Arrays.equals(trim(header), expectedHeader)) {
            throw new RuntimeException("Unexpected header line in input file.");
        }
    }

    static String[] trim(String[] data) {
        return Stream.of(data).map(it -> it.trim()).toArray(String[]::new);
    }

    static Product parseCsvRowToProduct(String[] data) {
        String[] trimmed = trim(data);
        double price = Double.parseDouble(trimmed[3]);
        return new Product(price, trimmed[0], trimmed[1], trimmed[2], trimmed[4]);
    }

    static String[] productToEuroRow(Product p) {
        String price = String.valueOf(p.priceInEuro());
        return new String[] { p.productId(), p.name(), p.description(), price, p.category() };
    }

}
