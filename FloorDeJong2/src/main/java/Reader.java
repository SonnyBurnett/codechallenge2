import board.TicTacToeBoard;
import board.TicTacToeBoardFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    private final ScannerFactory scannerFactory;
    private final TicTacToeBoardFactory boardFactory;

    public Reader(ScannerFactory scannerFactory, TicTacToeBoardFactory boardFactory) {
        this.scannerFactory = scannerFactory;
        this.boardFactory = boardFactory;
    }

    public Reader() {
        this(new ScannerFactory(), new TicTacToeBoardFactory());
    }

    public TicTacToeBoard readFile(String fileLocation) throws FileNotFoundException {
        File file = new File(fileLocation);
        TicTacToeBoard board = boardFactory.createBoard();

        try (Scanner scanner = scannerFactory.createScanner(file)) {
            int positionCount = -1;

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                for (int i=0; i<data.length(); i++) {
                    positionCount ++;
                    if (data.charAt(i)!='.') {
                        board.setPositionValue(positionCount, String.valueOf(data.charAt(i)));
                    }
                }
            }
        }
        return board;
    }

}