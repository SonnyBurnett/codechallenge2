import board.TicTacToeBoard;
import player.TicTacToePlayer;
import player.TicTacToePlayerFactory;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeGame {

    private final TicTacToePlayerFactory playerFactory;

    TicTacToeBoard board;
    Map<String, TicTacToePlayer> players = new HashMap<>();
    boolean isFinished;

    public TicTacToeGame(TicTacToeBoard board, TicTacToePlayerFactory playerFactory) {
        this.isFinished = false;
        this.board = board;
        this.playerFactory = playerFactory;
        this.createPlayers();
    }

    public TicTacToeGame(TicTacToeBoard board) {
        this(board, new TicTacToePlayerFactory());
    }

    public TicTacToeGame() {
        this(new TicTacToeBoard());
    }

    public void createPlayers() {
        this.players.put("X", this.playerFactory.createPlayer(1, "Player1", "X"));
        this.players.put("O", this.playerFactory.createPlayer(2, "Player2", "O"));
    }

    public String nextTurn() {
        TicTacToePlayer nextPlayer = determineNextPlayer();
        nextPlayer.doMove(this.board);

        TicTacToePlayer winner = hasWinner();
        if (winner != null) {
            return "WINNER " + winner.getSymbol();
        } else {
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
