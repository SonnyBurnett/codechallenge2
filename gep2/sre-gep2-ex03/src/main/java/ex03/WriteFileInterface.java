package ex03;

import java.io.IOException;

public interface WriteFileInterface {

    String getFileName();

    void setFileName(String fileName);

    void createFile() throws IOException;

    void writeFile(String outPut) throws IOException;
}