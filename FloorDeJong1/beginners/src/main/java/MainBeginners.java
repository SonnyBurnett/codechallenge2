import java.io.IOException;

public class MainBeginners {
    public static void main(String[] args) throws IOException {
        FileProcessor processor = new FileProcessor();
        processor.readFile("FloordeJong1/beginners/src/main/resources/001-beginners.csv");
        processor.writeAreaFile("FloordeJong1/beginners/src/main/resources/output.csv");
    }
}
