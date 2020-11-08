import board.Board;

public class TicTacToePlayer{
    private int id;
    private String name;
    private String symbol;

    public TicTacToePlayer(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    // ToDo: board methode check two same in row, with empty
    public void doMove(Board board) {

    }

    public String getSymbol() {
        return symbol;
    }
}
