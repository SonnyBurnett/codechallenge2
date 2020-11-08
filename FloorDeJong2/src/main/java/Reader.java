import board.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    public Board readFile(String fileLocation, Board board) throws FileNotFoundException {
        File file = new File(fileLocation);

        try (Scanner scanner = new Scanner(file)) {
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