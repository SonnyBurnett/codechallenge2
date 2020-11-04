public class Application {
    public static void main(String[] args) {
        new ProductProcessor("input.csv")
            .convertPrices(new CurrencyConverter(0.85f))
            .filterBelowPrice(10)
            .save("output.csv");
    }
}
