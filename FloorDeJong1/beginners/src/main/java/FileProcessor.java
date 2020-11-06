import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor {
    private final List<Figure2D> bodies = new ArrayList<>();

    public void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            bodies.clear();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" ");
                if (data[0] == "c") {
                    bodies.add(new Circle(Integer.parseInt(data[1])));
                } else if (data[0] == "s") {
                    bodies.add(new Square(Integer.parseInt(data[1])));
                } else {
                    throw new IllegalArgumentException("Incorrect request of 2D figure: "+(data[0])
                            + ", needs to be 'c' or 's'");
                }
            }
        }
    }

    public List<Figure2D> getBodies() {
        return bodies;
    }
}
