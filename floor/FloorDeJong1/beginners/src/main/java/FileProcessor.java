import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                if (data[0].equals("c")) {
                    bodies.add(new Circle(Integer.parseInt(data[1])/2.0));
                } else if (data[0].equals("s")) {
                    bodies.add(new Square(Integer.parseInt(data[1])/2.0));
                } else {
                    throw new IllegalArgumentException("Incorrect request of 2D figure: "+(data[0])
                            + ", needs to be 'c' or 's'");
                }
            }
        }
    }

    public void writeAreaFile(String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Figure2D figure: bodies) {
                writer.write(figure.getArea() + "\n");
            }
            System.out.println("Successfully wrote to file: " + fileName);
        }
    }

    public List<Figure2D> getBodies() {
        return bodies;
    }
}
