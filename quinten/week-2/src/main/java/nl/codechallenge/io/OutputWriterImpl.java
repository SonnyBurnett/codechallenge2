package nl.codechallenge.io;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class OutputWriterImpl implements OutputWriter {

    @Override
    public void write(String output) throws IOException {
        // To order to make the file writing unit testable I would need to create and inject a writer factory, if I
        // would want to keep the close next to the creation of the closeable resource (which is IO good practice). I
        // do not feel like doing that now as this would mean 20-30 lines of code extra.
        // This actually means I have nothing to test for OutputWriterImpl :(
        try (Writer writer = Files.newBufferedWriter(Paths.get("./outputs.csv"));) {
            writer.write(output);
        }
    }
}
