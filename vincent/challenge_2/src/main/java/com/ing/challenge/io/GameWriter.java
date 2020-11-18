package com.ing.challenge.io;

import java.io.File;
import java.io.IOException;

public interface GameWriter<T> {
    void write(File file, T obj) throws IOException;
}
