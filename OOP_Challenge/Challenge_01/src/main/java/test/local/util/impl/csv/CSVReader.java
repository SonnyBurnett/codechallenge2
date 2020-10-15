package test.local.util.impl.csv;

import test.local.util.FileReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CSVReader implements FileReader {
    private static final Logger LOGGER = Logger.getLogger(CSVReader.class.getName());

    public List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
            if (inputStream == null) {
                throw new NullPointerException("inputStream was null");
            }
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            streamReader.close();

        } catch (Exception ex) {
            LOGGER.warning("cannot open or read input file");
        }
        return lines;
    }
}
