package com.ing.challenge.parser;

import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;

public interface Parsable {
    void toCsv(File file) throws IOException;
}
