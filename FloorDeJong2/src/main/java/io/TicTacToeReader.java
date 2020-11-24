package io;

import model.board.TicTacToeBoard;
import model.board.TicTacToeBoardFactory;
import model.TicTacToeSymbol;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TicTacToeReader {

    private final TicTacToeBoardFactory boardFactory;

    public TicTacToeReader(TicTacToeBoardFactory boardFactory) {
        this.boardFactory = boardFactory;
    }

    public TicTacToeReader() {
        this( new TicTacToeBoardFactory());
    }

    public TicTacToeBoard readFile(String fileLocation) throws FileNotFoundException {
        File file = new File(fileLocation);
        TicTacToeBoard board = boardFactory.createBoard();

        try (Scanner scanner = new Scanner(file)) {
            int positionCount = -1;

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                for (int i=0; i<data.length(); i++) {
                    positionCount ++;
                    if (data.charAt(i)!='.') {
                        board.setPositionValue(positionCount, getSymbol(String.valueOf(data.charAt(i))));
                    }
                }
            }
        }
        return board;
    }

    TicTacToeSymbol getSymbol(String character) {
        TicTacToeSymbol[] symbols = TicTacToeSymbol.class.getEnumConstants();
        for (TicTacToeSymbol symbol: symbols) {
            if (symbol.toString().equals(character)) {
                return symbol;
            }
        }

        System.out.printf("Error: %s is not a correct TicTacToeSymbol%n", character);
        return null;
    }
}