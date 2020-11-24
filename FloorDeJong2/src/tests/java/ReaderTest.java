import board.TicTacToeBoard;
import board.TicTacToeBoardFactory;
import org.junit.jupiter.api.Test;
import player.TicTacToeSymbol;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ReaderTest {

    private final Scanner mockScanner = mock(Scanner.class);
    private final ScannerFactory mockScannerFactory = mock(ScannerFactory.class);
    {
        try {
            when(mockScannerFactory.createScanner(any())).thenReturn(mockScanner);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private TicTacToeBoard board = new TicTacToeBoard();
    private final TicTacToeBoard spyBoard = spy(board);
    private final TicTacToeBoardFactory mockBoardFactory = mock(TicTacToeBoardFactory.class);
    { when(mockBoardFactory.createBoard()).thenReturn(spyBoard); }

    private final Reader reader = new Reader(mockScannerFactory, mockBoardFactory);

    @Test
    public void testReadFile() throws FileNotFoundException {
        // Assign
        String line1 = ".XO";
        String line2 = "X.O";
        when(mockScanner.nextLine()).thenReturn(line1).thenReturn(line2);

        // act
        TicTacToeBoard board = reader.readFile("aFile");

        // Assert

        verify(spyBoard, times(1)).setPositionValue(1, TicTacToeSymbol.X);
        verify(spyBoard, times(1)).setPositionValue(3, TicTacToeSymbol.X);
        verify(spyBoard, times(1)).setPositionValue(2, TicTacToeSymbol.O);
        verify(spyBoard, times(1)).setPositionValue(5, TicTacToeSymbol.O);
        verifyNoMoreInteractions(spyBoard);
    }
}
