package nl.codechallenge.io;

import nl.codechallenge.model.Game;

import java.io.IOException;
import java.nio.file.Path;

public interface TicTactToeFileReader {
    Game read(Path file) throws IOException;
}
