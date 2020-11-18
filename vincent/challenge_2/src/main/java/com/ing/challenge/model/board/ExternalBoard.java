package com.ing.challenge.model.board;

import java.io.File;
import java.io.IOException;

public interface ExternalBoard {
    Board boardFromFile(File file) throws IOException;
}
