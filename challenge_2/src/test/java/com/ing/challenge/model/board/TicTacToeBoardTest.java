package com.ing.challenge.model.board;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.ing.challenge.GameApp;
import com.ing.challenge.model.board.TicTacToeBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static com.google.common.truth.Truth.assertThat;

public class TicTacToeBoardTest {
    @Test
    void initBoard() {
        var board = new TicTacToeBoard();
        board.initializeBoard();
        assertThat(board.getBoard()).asList().hasSize(9);
        assertThat(board.getBoard()).asList().containsExactly('.','.','.','.','.','.','.','.','.');
    }

    @Test
    void readFailingBoardFromFile() {
        var board = new TicTacToeBoard();
        var file = Paths.get("src/test/resources","007-failing.txt").toFile();
        var err = Assertions.assertThrows(IllegalArgumentException.class, () -> board.boardFromFile(file));
        assertThat(err).hasMessageThat().isEqualTo("The loaded board should contain three rows with only a " +
                "'X', 'O' or '.' character.");

        var fileIoError = Paths.get("src/test/resources","failing//Fail//.txt").toFile();
        var ioErr = Assertions.assertThrows(IOException.class, () -> board.boardFromFile(fileIoError));
        assertThat(ioErr).hasMessageThat().isEqualTo(TicTacToeBoard.errorMessage);
    }

    @Test
    void failingConstructor() throws Exception {
        var file = Paths.get("src/test/resources","failing//Fail//.txt").toFile();
        int exitCode = SystemLambda.catchSystemExit(() -> new TicTacToeBoard(file));
        assertThat(exitCode).isEqualTo(1);
    }
}
