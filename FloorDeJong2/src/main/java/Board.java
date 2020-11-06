import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.EnumUtils;

public class Board {

    private List<List<String>> board;
    public enum boardValues
    {
        NULL,
        X,
        O
    }

    public Board(List<List<String>> board) {
        if (checkBoardSizes(board)) {
            this.board = new ArrayList<>(board);
        } else {
            throw new IllegalArgumentException("The sizes of given board is incorrect.");
        }
    }

    public Board() {
        this.board = this.generateBlankBoard();
    }

    private List<List<String>> generateBlankBoard() {
        List<List<String>> blankBoard = new ArrayList<>();
        for (int i=0 ; i<3 ; i++) {
            blankBoard.add(new ArrayList<>(3));
        }

        return blankBoard;
    }

    public List<List<String>> getBoard() {
        return board;
    }

    public void setBoard(List<List<String>> board) {
        this.board = board;
    }

    private boolean checkBoardSizes(List<List<String>> board) {
        if (board.size() == 3) {
            for (int i=0; i<3; i++) {
                if (board.get(i).size() != 3) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkBoardValues(List<List<String>> board) {
        for (int i=0; i<3; i++) {
            for (int j=0; i<3; i++) {
                if (EnumUtils.isValidEnum(boardValues, board.get(i).get(j)));
            }
        }
        return true;
    }
}
