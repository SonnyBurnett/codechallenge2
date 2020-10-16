import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, FileNotFoundException {
        String input = "FloordeJong1/src/main/resources/input.csv";
        ProductFilter filter = new ProductFilter();
        filter.readProductFile(input);
        filter.createFilteredConvertedFile("output.csv");
    }
}
