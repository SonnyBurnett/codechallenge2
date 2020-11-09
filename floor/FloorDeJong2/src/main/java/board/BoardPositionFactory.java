package board;

public class BoardPositionFactory {
    public BoardPosition createBoardPosition(int i) {
        return new BoardPosition(i);
    }
}
