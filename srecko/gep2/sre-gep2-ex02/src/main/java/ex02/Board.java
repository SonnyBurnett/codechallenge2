package ex02;

import java.util.Arrays;

public class Board {
    private final static int rows = 3;
    private final static int columns = 3;
    private final Cell[][] board;

    public Board() {
        this.board = new Cell[rows][columns];
        for (int j = 1; j <= rows; j++) {
            for (int i = 1; i <= columns; i++) {
                this.board[j - 1][i - 1] = new Cell();
            }
        }
    }

    public Board(String[] streamOfChars) {
        int cellWalk = 0;

        this.board = new Cell[rows][columns];
        for (int j = 1; j <= rows; j++) {
            for (int i = 1; i <= columns; i++) {
                Cell temp = new Cell();
                temp.setCellValue(streamOfChars[cellWalk]);
                this.board[j - 1][i - 1] = temp;
                cellWalk++;
            }
        }
    }

    @SuppressWarnings("unused")
    public void print() {
        System.out.print("-------\n");
        for (int j = 1; j <= rows; j++) {
            System.out.print("|");
            for (int i = 1; i <= columns; i++) {
                for (String s : Arrays.asList(board[j - 1][i - 1].getCellValue(), "|")) {
                    System.out.print(s);
                }
            }
            System.out.print("\n-------\n");
        }
    }

    public String toString(String in) {
        StringBuilder out = new StringBuilder();
        int cellNumber = 0;
        for (int j = 1; j <= rows; j++) {
            for (int i = 1; i <= columns; i++) {
                if (board[j - 1][i - 1].getCellValue().equals(in)) {
                    out.append(cellNumber);
                }
                cellNumber++;
            }
        }
        return out.toString();
    }
}
