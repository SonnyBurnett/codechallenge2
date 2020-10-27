public class Main {
    public static void main(String[] args) {
        String input = "FloordeJong1/advanced/src/main/resources/input.csv";
        ProductFilter filter = new ProductFilter();
        filter.readProductFile(input);
        filter.createFilteredConvertedFile("FloordeJong1/advanced/src/main/resources/output.csv", 10, Currency.Type.EURO);
    }
}
