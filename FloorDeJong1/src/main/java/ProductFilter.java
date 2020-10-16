import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;


// ToDo: Tests
// ToDo: logging
public class ProductFilter {
    private List<Product> inputList = new ArrayList<>();
    private List<Product> outputList = new ArrayList<>();

    public void readProductFile(String fileName) throws FileNotFoundException {
        inputList.clear();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        if(Product.checkProductInfo(scanner.nextLine())) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                inputList.add(new Product(Long.parseLong(data[0]), data[1].strip(), data[2].strip(),
                        Double.parseDouble(data[3]), data[4].strip()));
            }
        }
        scanner.close();
    }

    public void createFilteredConvertedFile(String fileName) throws CloneNotSupportedException {
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

    // ToDo: create class for currency change
    public List<Product> convertCurrency(List<Product> productList, String newCurrency, double rate) throws CloneNotSupportedException {
        List<Product> convertedList = cloneList(productList);

        for (Product product : convertedList) {
            product.convertCurrency(newCurrency, rate);
        }
        return convertedList;
    }

    // TODO: option: check whether a file already exists
    // TODO: Let new file be output in same folder as input
    public void writeProductFile(List<Product> productList, String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(Product.PRODUCT_INFO);
            for (Product product: productList) {
                myWriter.write(product.toString() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<Product> cloneList(List<Product> oldList) throws CloneNotSupportedException {
        List<Product> newList = new ArrayList<>();
        for (Product product: oldList) {
            newList.add(product.clone());
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

