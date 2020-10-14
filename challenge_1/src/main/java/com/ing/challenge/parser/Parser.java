package com.ing.challenge.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * A {@link com.ing.challenge.parser.Parser} let's you read and write files of different types.
 * @param <T> A object type
 */
public interface Parser<T extends Parsable> {
    /**
     * Read lines from a file.
     * @param fileName name of the file
     * @return A {@link List} of Object of type T
     * @see Parser#read(File)
     */
    List<T> read(String fileName);
    /**
     * Read lines from a {@link File}.
     * @param file A {@link File} object relating to A file on the system
     * @return A {@link List} of Object of type T
     * @see Parser#read(String)
     */
    List<T> read(File file);
    boolean write(File file, T object) throws IOException;
    boolean write(File file, List<T> objects) throws IOException;
}
