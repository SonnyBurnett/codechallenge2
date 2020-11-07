import board.TicTacToeBoard;

import java.io.FileNotFoundException;

public class Assignment2 {

    public void run() throws FileNotFoundException {
        String inputFile = "FloordeJong2/src/main/resources/input.txt";
        String outputFile = "FloordeJong2/src/main/resources/output.csv";

        TicTacToeBoard board = new TicTacToeBoard();
        new Reader().readFile(inputFile, board);
        String nextMove = new TicTacToeGame(board).nextTurn();
//        new Writer.writeNextMove(outputFile);
    }
}
