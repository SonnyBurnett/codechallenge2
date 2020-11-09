package player;

public class TicTacToePlayerFactory {
    public TicTacToePlayer createPlayer(int id, String name, String symbol) {
        return new TicTacToePlayer(id, name, symbol);
    }
}
