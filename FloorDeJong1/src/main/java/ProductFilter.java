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
        try {
            inputList.clear();
            File file = new File(fileLocation);
            Scanner scanner = new Scanner(file);
            if (Product.checkProductInfo(scanner.nextLine())) {
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    inputList.add(new Product(Long.parseLong(data[0]), data[1].strip(), data[2].strip(),
                            Double.parseDouble(data[3]), data[4].strip()));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){
            LOGGER.error(e.toString());
        }
    }

    public void createFilteredConvertedFile(String fileName) {
        List<Product> filterList = filterOut(inputList, p -> p.checkPriceBelow(10));
        outputList = convertCurrency(filterList, "EURO", 0.85);
        writeProductFile(outputList, fileName);
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

    // ToDo: create class for currency, with a methode convertCurrency
    public List<Product> convertCurrency(List<Product> productList, String newCurrency, double rate) {
        List<Product> convertedList = cloneList(productList);

        for (Product product : convertedList) {
            product.convertCurrency(newCurrency, rate);
        }
        return convertedList;
    }

    // TODO: option: check whether a file already exists
    public void writeProductFile(List<Product> productList, String fileLocation) {
        try {
            FileWriter writer = new FileWriter(fileLocation);
            writer.write(Product.PRODUCT_INFO);
            for (Product product: productList) {
                writer.write(product.toString() + "\n");
            }
            writer.close();
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

