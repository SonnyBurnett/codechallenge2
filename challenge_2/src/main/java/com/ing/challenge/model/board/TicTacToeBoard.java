package com.ing.challenge.model.board;

import com.ing.challenge.io.BoardReader;
import com.ing.challenge.io.GameReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TicTacToeBoard implements Board, ExternalBoard {
    private static final Logger logger = LogManager.getLogger(TicTacToeBoard.class);
    protected static final String errorMessage = "Failed to read external board!";
    private char[] gameBoard;

    public TicTacToeBoard() {
        gameBoard = new char[9];
        initializeBoard();
    }

    public TicTacToeBoard(File file) {
        try {
            boardFromFile(file);
        } catch (IOException e) {
            logger.error(() -> errorMessage, e);
            System.exit(1);
        }
    }

    @Override
    public void initializeBoard() {
        Arrays.fill(gameBoard, '.');
    }

    @Override
    public char[] getBoard() {
        return gameBoard;
    }

    @Override
    public Board boardFromFile(File file) throws IOException {
        try {
            GameReader<char[]> reader = new BoardReader();
            gameBoard = reader.read(file);
            return this;
        } catch (IOException e) {
            logger.error(() -> errorMessage, e);
            throw new IOException(errorMessage,e);
        }
    }
}
