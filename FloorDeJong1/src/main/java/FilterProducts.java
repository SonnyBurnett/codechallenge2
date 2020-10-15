import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;


// ToDo: Logging
// ToDo: Tests
// ToDo: Create productList class
public class FilterProducts {
    private List<Product> inputList = new ArrayList<>();
    private List<Product> outputList;

    public FilterProducts(String file) {
        readProductFile(file);
    }

    // ToDo: Check whether first line is can be used for as products
    public void readProductFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                inputList.add(new Product(Long.parseLong(data[0]), data[1], data[2], Double.parseDouble(data[3]), data[4]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void createFilteredConvertedFile() throws CloneNotSupportedException {
        List<Product> filterList = filterOut(inputList, p -> p.priceIsBelow(10));
        outputList = convertCurrency(filterList, "EURO", 0.85);
        writeProductFile(outputList);
    }

    // ToDo: Use sort and find first product >= 10, remove rest
    public List<Product> filterOut(List<Product> productList, Predicate<Product> checker) {
        List<Product> filteredList = new ArrayList<>(productList);
        List<Product> toBeFilteredOut = new ArrayList<>();

        for (Product product: filteredList) {
            if (checker.test(product)) {
                toBeFilteredOut.add(product);
            }
        }

        for (Product product: toBeFilteredOut) {
            filteredList.remove(product);
        }

        return filteredList;
    }

    // ToDo: create class for currency change
    public List<Product> convertCurrency(List<Product> productList, String newCurrency, double rate) throws CloneNotSupportedException {
        List<Product> convertedList = cloneList(productList);

        for (Product product : convertedList) {
            if (!product.getCurrency().equals(newCurrency)) {
                product.setPrice(product.getPrice() * rate);
                product.setCurrency(newCurrency);
            }
        }
        return convertedList;
    }

    // TODO: option: check whether a file already exists
    public void writeProductFile(List<Product> productList) {
        try {
            FileWriter myWriter = new FileWriter("output.csv");
            myWriter.write("productId, name, description, price, category\n");
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

