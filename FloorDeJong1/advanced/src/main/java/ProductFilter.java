import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class ProductFilter {
    private List<Product> inputList = new ArrayList<>();
    private List<Product> outputList = new ArrayList<>();
    static final Logger LOGGER = LoggerFactory.getLogger(ProductFilter .class);

    public void readProductFile(String fileLocation) {
        File file = new File(fileLocation);
        try (Scanner scanner = new Scanner(file)) {
            inputList.clear();
            if (Product.checkProductInfo(scanner.nextLine())) {
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    inputList.add(new Product(Long.parseLong(data[0]), data[1].strip(), data[2].strip(),
                            Double.parseDouble(data[3]), data[4].strip()));
                }
            }
        } catch (FileNotFoundException e){
            LOGGER.error(e.toString());
        }
    }

    public void createFilteredConvertedFile(String newFileName, double maxPrice, Currency.Type newCurrency) {
        List<Product> filterList = filterOut(inputList, p -> p.checkPriceBelow(maxPrice));
        outputList = convertCurrency(filterList, newCurrency);
        writeProductFile(outputList, newFileName);
    }

    public List<Product> filterOut(List<Product> productList, Predicate<Product> checker) {
        List<Product> filteredList = new ArrayList<>();

        for (Product product: productList) {
            if (!(checker.test(product))) {
                filteredList.add(product);
            }
        }

        return filteredList;
    }

    public List<Product> convertCurrency(List<Product> productList, Currency.Type newCurrency) {
        List<Product> convertedList = cloneList(productList);

        for (Product product : convertedList) {
            product.convertCurrency(newCurrency);
        }
        return convertedList;
    }

    // TODO: option: check whether a file already exists
    // ToDo: use FileWriterFactory for not using new FileWriter...
    public void writeProductFile(List<Product> productList, String fileLocation) {
        try (FileWriter writer = new FileWriter(fileLocation)) {
            writer.write(Product.PRODUCT_INFO);
            for (Product product: productList) {
                writer.write(product.toString() + "\n");
            }
            LOGGER.info("Successfully wrote to file: " + fileLocation);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    }

    public List<Product> cloneList(List<Product> oldList) {
        List<Product> newList = new ArrayList<>();
        for (Product product: oldList) {
            try {
                newList.add(product.clone());
            } catch (CloneNotSupportedException e) {
                LOGGER.error(e.toString());
            }
        }
        return newList;
    }

    public List<Product> getInputList() {
        return inputList;
    }

    public List<Product> getOutputList() {
        return outputList;
    }
}

