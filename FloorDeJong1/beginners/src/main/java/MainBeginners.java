import java.io.FileNotFoundException;

public class MainBeginners {
    public static void main(String[] args) throws FileNotFoundException {
        FileProcessor processor = new FileProcessor();
        processor.readFile("FloordeJong1/beginners/src/main/resources/001-beginners.csv");
    }
}
