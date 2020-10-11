package local.test;


import local.test.model.Product;
import local.test.util.csv.CSV2ProductParser;
import local.test.util.csv.CSVPrinter;
import local.test.util.csv.CSVReader;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodingChallenge {

        /*
Write a program that:​
- Reads all inputs.csv​
- Writes a file outputs.csv that:​
    - Contains the same products as from products.csv​
    - Leaving out all products with a price below $ 10​
    - Writes out prices in euro’s, assuming a rate of $ 1 : € 0,85​

Input file:
productId,	name,	description,	price,	category​
45848,	shorts,	short pants,	8,	pants​
4184688,	trousers,	trousers,	12,	pants​
848488,	blue shirt,	shirt,	88,	shirts

Output file:​
productId,	name,	description,	price,	category​
4184688,	trousers,	trousers,	10.2,	pants​
848488,	blue shirt,	shirt,	74.8,	shirts
     */
    public static void main(String[] args) {
        new CodingChallenge().run();
    }

    public static final String HEADER_ROW = "productId, name, description, price, category";

    public void run() {
        List<Product> filteredProducts = new CSVReader().readFile("input.csv")
                .stream()
                .skip(1) // skip heading row
                .map(CSV2ProductParser::getProductFromCSVLine)
                .filter(product -> product != Product.invalid())
                .filter(product -> product.getDollarPrice() > 10)
                .collect(Collectors.toList());
        writeOutput(filteredProducts);
    }



    private void writeOutput(List<Product> products) {
        List<String> dataToPrint = mergeHeaderWithProducts(products);
        new CSVPrinter().printFile("src/main/resources/output.csv", dataToPrint);
    }

    protected List<String> mergeHeaderWithProducts(List<Product> products) {
        return Stream.of(
                List.of(HEADER_ROW),
                convertProductToCSVStringList(products)
        )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    protected List<String> convertProductToCSVStringList(List<Product> products) {
        return products
                .stream()
                .map(Product::toCSVString)
                .collect(Collectors.toList());
    }


}
