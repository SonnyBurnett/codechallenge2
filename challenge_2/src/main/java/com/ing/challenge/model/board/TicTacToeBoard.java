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
    private char[] gameBoard;

    public TicTacToeBoard() {
        gameBoard = new char[9];
        initializeBoard();
    }
    public TicTacToeBoard(File file) {
        boardFromFile(file);
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
    public Board boardFromFile(File file) {
        GameReader<char[]> reader = new BoardReader();
        try {
            gameBoard = reader.read(file);
        } catch (IOException e) {
            logger.error(() -> "could not read file", e);
        }
        return this;
    }
}
