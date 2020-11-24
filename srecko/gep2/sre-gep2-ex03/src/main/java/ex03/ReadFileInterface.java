package ex03;

import java.io.IOException;

public interface ReadFileInterface {

    String getFileName() ;

    void setFileName(String fileName) ;

    void readFile() throws IOException ;
}