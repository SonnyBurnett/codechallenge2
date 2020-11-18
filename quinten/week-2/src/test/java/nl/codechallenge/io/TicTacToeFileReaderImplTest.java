package nl.codechallenge.io;

import nl.codechallenge.model.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static nl.codechallenge.model.Field.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TicTacToeFileReaderImplTest {

    TicTactToeFileReader reader = new TicTactToeFileReaderImpl();

    /**
     * ...
     * ...
     * ...
     */
    @Test
    void readEmptyBoard() throws IOException {
        Path path = Paths.get("src/test/resources/empty_board.txt");

        assertThat(reader.read(path), equalTo(new Game(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE)));
    }

    /**
     * X.O
     * .XO
     * OX.
     */
    @Test
    void readMixedBoard() throws IOException {
        Path path = Paths.get("src/test/resources/mixed_board.txt");

        assertThat(reader.read(path), equalTo(new Game(X, NONE, O, NONE, X, O, O, X, NONE)));
    }

    /**
     * X..
     * .O.
     * O.X
     */
    @Test
    void readExerciseInput() throws IOException {
        Path path = Paths.get("src/test/resources/inputs.txt");

        assertThat(reader.read(path), equalTo(new Game(X, X, X, O, O, X, O, O, NONE)));
    }
}
