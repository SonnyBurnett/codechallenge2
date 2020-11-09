import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public void writeNextMove(String fileLocation, String text) throws IOException {
        try (FileWriter writer = new FileWriter(fileLocation)) {
            writer.write(text);
        }
    }
}
