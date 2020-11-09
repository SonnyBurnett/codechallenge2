package nl.codechallenge;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface InputToOutputConverterAndWriter {

    public void createOutputs(String absoluteFilePath) throws IllegalStateException, FileNotFoundException, IOException;

}
