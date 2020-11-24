import board.TicTacToeBoard;
import player.TicTacToePlayer;

import java.io.IOException;

public class Assignment2 {

    public void run() throws IOException {
        String inputFile = "FloordeJong2/src/main/resources/input.txt";
        String outputFile = "FloordeJong2/src/main/resources/output.csv";

        TicTacToePlayer player1 = new TicTacToePlayer(1, "Floor", "X");
        TicTacToePlayer player2 = new TicTacToePlayer(2, "Freek", "O");

        TicTacToeBoard board = new Reader().readFile(inputFile);
        String nextMove = new TicTacToeGame(board, player1, player2).nextTurn();
        new Writer().writeNextMove(outputFile, nextMove);
    }
}
