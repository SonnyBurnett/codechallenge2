package com.ing.challenge.io;

import java.io.File;
import java.io.IOException;

public interface GameReader<T> {
    T read(File file) throws IOException;
}
