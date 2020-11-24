package io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterFactory {
    public FileWriter create(String fileLocation) throws IOException {
        return new FileWriter(fileLocation);
    }
}
