import board.TicTacToeBoard;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeGame {
    TicTacToeBoard board;
    Map<String, TicTacToePlayer> players = new HashMap<>();
    boolean isFinished;
    TicTacToePlayer winner;

    public TicTacToeGame(TicTacToeBoard board) {
        this.isFinished = false;
        this.board = board;
        this.createPlayers();
    }

    public TicTacToeGame() {
        this(new TicTacToeBoard());
    }

    public void createPlayers() {
        this.players.put("X", new TicTacToePlayer(1, "Player1", "X"));
        this.players.put("O", new TicTacToePlayer(2, "Player2", "O"));
    }

    public String nextTurn() {
        if (hasWinner()) {
            return "WINNER " + this.winner.getSymbol();
        }

        TicTacToePlayer nextPlayer = determineNextPlayer();
        return "NEXTMOVE " + nextPlayer.getSymbol();
    }

    public TicTacToePlayer determineNextPlayer() {
        int nrTurnsPlayed = this.board.getNumberOccupiedPositions();
        if (nrTurnsPlayed % 2 == 0) {
            return this.players.get("X");
        } else {
            return this.players.get("O");
        }
    }

    public boolean hasWinner() {
        String winningSymbol = this.board.threeInRow();
        if (winningSymbol != null) {
            this.isFinished = true;
            this.winner = this.players.get(winningSymbol);
            return true;
        }
        return false;
    }

    public Map<String, TicTacToePlayer> getPlayers() {
        return this.players;
    }
}
