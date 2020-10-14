import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class FilterProducts {
    private List<Product> inputList = new ArrayList<>();
    private List<Product> outputList;

    public FilterProducts(String file) throws CloneNotSupportedException {
        readProductFile(file);
        List<Product> filterList = filterOut(inputList, p -> p.priceIsBelow(10));
        List<Product> convertedList = convertCurrency(filterList, "EURO", 0.85);
        System.out.println(filterList);
        System.out.println(convertedList);
    }

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

    public List<Product> filterOut(List<Product> productList, Predicate<Product> checker) throws CloneNotSupportedException {
        List<Product> filteredList = cloneList(inputList);
        for (Product product: productList) {
            if (checker.test(product)) {
                 filteredList.remove(product);
            }
        }
        return filteredList;
    }

    public List<Product> convertCurrency(List<Product> productList, String newCurrency, double rate) throws CloneNotSupportedException {
        List<Product> convertedList = cloneList(productList);

        for (Product product: convertedList) {
            if (!product.getCurrency().equals(newCurrency)) {
                product.setPrice(product.getPrice() * rate);
                product.setCurrency(newCurrency);
            }
        }
        return convertedList;
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

