public class Application {
    public static void main(String[] args) {
        new ProductProcessor("input.csv")
            .filterBelowPrice(10)
            .save("output.csv");
    }
}
