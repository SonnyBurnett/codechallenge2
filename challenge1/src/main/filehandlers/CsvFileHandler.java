package main.filehandlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvFileHandler implements IFileHandler {

    private String delimiter;

    public CsvFileHandler(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public List<List<String>> readFileContent(String filepath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filepath));
            List<List<String>> fileContent = new ArrayList<>();
            while (scanner.hasNextLine()) {
                fileContent.add(readCSVLineContent(scanner.nextLine()));
            }
            return fileContent;

        } catch(IOException exception){
            System.out.println("Encountered error while reading the file" + exception.getMessage());
            return null;
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }finally{
            scanner.close();
        }

    }

    @Override
    public void writeFileContent(String filepath, List<List<String>> records) {

        try{
            FileWriter csvWriter = new FileWriter(filepath);
            for (List<String> rowData : records) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> readCSVLineContent(String line) {
        List<String> lineValues = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(this.delimiter);
            while (rowScanner.hasNext()) {
                lineValues.add(rowScanner.next().trim());
            }
        }
        return lineValues;
    }
}
