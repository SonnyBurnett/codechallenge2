package main.filehandlers;


import java.io.File;
import java.util.List;

public interface IFileHandler {
    default boolean doesFileExist(String filepath){
        File file = new File(filepath);
        return file.isFile();

    }
    List<List<String>> readFileContent(String filepath);
    public void writeFileContent(String filepath, List<List<String>> records);
}
