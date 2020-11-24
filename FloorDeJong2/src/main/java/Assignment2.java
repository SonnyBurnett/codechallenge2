import model.board.TicTacToeBoard;
import io.TicTacToeReader;
import io.TicTacToeWriter;
import model.TicTacToeGame;
import model.TicTacToePlayer;
import model.TicTacToeSymbol;

import java.io.IOException;

public class Assignment2 {

    public void run() throws IOException {
        String inputFile = "FloordeJong2/src/main/resources/input.txt";
        String outputFile = "FloordeJong2/src/main/resources/output.csv";

        TicTacToePlayer player1 = new TicTacToePlayer(1, "Floor", TicTacToeSymbol.X);
        TicTacToePlayer player2 = new TicTacToePlayer(2, "Freek", TicTacToeSymbol.O);

        TicTacToeBoard board = new TicTacToeReader().readFile(inputFile);
        String nextMove = new TicTacToeGame(board, player1, player2).nextTurn();
        new TicTacToeWriter().writeNextMove(outputFile, nextMove);
    }
}
