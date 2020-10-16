public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        String input = "FloordeJong1/src/main/resources/input.csv";
        FilterProducts filter = new FilterProducts(input);
        filter.createFilteredConvertedFile();
    }
}
