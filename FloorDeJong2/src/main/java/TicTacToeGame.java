import board.TicTacToeBoard;
import player.TicTacToePlayer;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeGame {

    TicTacToeBoard board;
    Map<String, TicTacToePlayer> players = new HashMap<>();
    boolean isFinished;

//    ToDo: check whether players have different symbols
    public TicTacToeGame(TicTacToeBoard board, TicTacToePlayer player1, TicTacToePlayer player2) {
        this.isFinished = false;
        this.board = board;
        this.players.put(player1.getSymbol(), player1);
        this.players.put(player2.getSymbol(), player2);
    }

    public String nextTurn() {

        TicTacToePlayer winner = hasWinner();
        if (winner != null) {
            return "WINNER " + winner.getSymbol();
        } else {
            TicTacToePlayer nextPlayer = determineNextPlayer();
            nextPlayer.doMove(board);
            return "NEXTMOVE " + nextPlayer.getSymbol();
        }
    }

    public TicTacToePlayer determineNextPlayer() {
        int nrTurnsPlayed = this.board.getNumberOccupiedPositions();
        if (nrTurnsPlayed % 2 == 0) {
            return this.players.get("X");
        } else {
            return this.players.get("O");
        }
    }

    public TicTacToePlayer hasWinner() {
        String winningSymbol = this.board.hasThreeInRow();
        if (winningSymbol != null) {
            this.isFinished = true;
            return this.players.get(winningSymbol);
        }
        return null;
    }

    public Map<String, TicTacToePlayer> getPlayers() {
        return this.players;
    }
}
