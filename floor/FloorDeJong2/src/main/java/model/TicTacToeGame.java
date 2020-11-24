package model;

import model.board.TicTacToeBoard;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeGame {

    TicTacToeBoard board;
    Map<TicTacToeSymbol, TicTacToePlayer> players = new HashMap<>();
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
            return this.players.get(TicTacToeSymbol.X);
        } else {
            return this.players.get(TicTacToeSymbol.O);
        }
    }

    public TicTacToePlayer hasWinner() {
        String winningSymbol = this.board.hasThreeInRow();
        if (winningSymbol != null) {
            this.isFinished = true;
            for (TicTacToePlayer player: players.values()) {
                if (player.getSymbol().toString().equals(winningSymbol)) {
                    return player;
                } else {
                    System.out.println("Error: Winning symbol is not one of the TicTacToeSymbols. received: " + winningSymbol);
                }
            }
        }
        return null;
    }

    public Map<TicTacToeSymbol, TicTacToePlayer> getPlayers() {
        return this.players;
    }
}
