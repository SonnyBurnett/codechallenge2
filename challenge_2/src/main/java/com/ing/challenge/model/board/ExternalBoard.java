package com.ing.challenge.model.board;

import java.io.File;

public interface ExternalBoard {
    Board boardFromFile(File file);
}
