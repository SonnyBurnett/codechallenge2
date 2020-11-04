package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader {
    private String path;
    private String headers;
    protected List<String[]> lines;

    public CSVReader(String providedPath) {
        path = providedPath;
        this.setLines();
    }

    protected void setLines() {
        if (!(this.path.length() > 0)) throw new IllegalStateException();

        ClassLoader loader = getClass().getClassLoader();

        try (InputStream input = loader.getResourceAsStream(path)) {
            InputStreamReader streamReader = new InputStreamReader(input, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            // Save first line as header for output file.
            headers = bufferedReader.readLine();
            lines = bufferedReader.lines()
                .map(line -> trimLines(line))
                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] trimLines(String input) {
        return Arrays.stream(input.split(",")).map(it -> it.trim()).toArray(String[]::new);
    }

    protected String formatLine(String[] fields) {
        return String.join(", ", fields);
    }

    public List<String[]> getLines() {
        return lines;
    }

    public void setLines(List<String[]> newLines) {
        lines = newLines;
    }

    public void save(String outputFile) {
        File output = new File(outputFile);

        try (PrintWriter writer = new PrintWriter(output)) {
            output.createNewFile();
            writer.println(headers);
            lines.stream()
                    .map(this::formatLine)
                    .forEach(writer::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
