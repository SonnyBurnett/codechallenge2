package player;

import board.BoardPosition;
import board.TicTacToeBoard;

public class TicTacToePlayer{
    private int id;
    private String name;
    private String symbol;

    // ToDo: make symbol an enum
    public TicTacToePlayer(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public void doMove(TicTacToeBoard board) {
        BoardPosition position = board.gettingWinningPosition(this.symbol);

        if (position != null) {
            board.setPositionValue(position.getPositionId(), this.symbol);
        }

        // Logic for move if not possible to create three in a row.
    }

    public String getSymbol() {
        return symbol;
    }
}
