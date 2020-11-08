import board.TicTacToeBoard;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeGame {
    TicTacToeBoard board;
    Map<String, TicTacToePlayer> players = new HashMap<>();
    boolean isFinished;

    public TicTacToeGame(TicTacToeBoard board) {
        this.isFinished = false;
        this.board = board;
        this.createPlayers();
    }

    public TicTacToeGame() {
        this(new TicTacToeBoard());
    }

    public void createPlayers() {
        players.put("X", new TicTacToePlayer(1, "Player1", "X"));
        players.put("O", new TicTacToePlayer(2, "Player2", "O"));
    }

    public String nextTurn() {
        return "";
    }

    public Map<String, TicTacToePlayer> getPlayers() {
        return players;
    }
}
