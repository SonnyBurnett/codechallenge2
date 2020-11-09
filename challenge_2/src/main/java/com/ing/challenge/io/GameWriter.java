package com.ing.challenge.io;

import java.io.File;

public interface GameWriter<T> {
    void write(File file, T obj);
}
