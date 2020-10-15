package local.test;

import local.test.model.Product;
import local.test.util.FilePrinter;
import local.test.util.FileReader;
import local.test.util.ListManipulator;
import local.test.util.impl.csv.CSV2ProductParser;
import local.test.util.impl.csv.CSVPrinter;
import local.test.util.impl.csv.CSVReader;

import java.util.List;
import java.util.stream.Collectors;

public class CodingChallenge {

    public static void main(String[] args) {
        new CodingChallenge().run();
    }

    public static final String HEADER_ROW = "productId, name, description, price, category";
    private final FileReader fileReader = new CSVReader();
    private final FilePrinter filePrinter = new CSVPrinter();
    private final ListManipulator listManipulator = new ListManipulator();

    public void run() {
        List<Product> filteredProducts = fileReader.readFile("input.csv")
                .stream()
                .skip(1) // skip heading row
                .map(CSV2ProductParser::getProductFromCSVLine)
                .filter(product -> product != Product.invalid())
                .filter(product -> product.getDollarPrice() > 10)
                .collect(Collectors.toList());

        filePrinter.printFile("src/main/resources/output.csv", listManipulator.mergeHeaderWithProducts(filteredProducts));
    }
}
