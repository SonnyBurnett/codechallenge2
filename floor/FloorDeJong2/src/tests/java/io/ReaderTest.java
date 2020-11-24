package io;

import model.board.TicTacToeBoard;
import model.board.TicTacToeBoardFactory;
import org.junit.jupiter.api.Test;
import model.TicTacToeSymbol;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ReaderTest {

    private TicTacToeBoard board = new TicTacToeBoard();
    private final TicTacToeBoard spyBoard = spy(board);
    private final TicTacToeBoardFactory mockBoardFactory = mock(TicTacToeBoardFactory.class);
    { when(mockBoardFactory.createBoard()).thenReturn(spyBoard); }

    private final TicTacToeReader reader = new TicTacToeReader(mockBoardFactory);

    @Test
    public void testReadFile() throws FileNotFoundException {
        // Assign

        String fileLocation = "FloordeJong2/src/tests/resources/input.txt";

        // act
        TicTacToeBoard board = reader.readFile(fileLocation);

        // Assert

        verify(spyBoard, times(1)).setPositionValue(0, TicTacToeSymbol.X);
        verify(spyBoard, times(1)).setPositionValue(8, TicTacToeSymbol.X);
        verify(spyBoard, times(1)).setPositionValue(4, TicTacToeSymbol.O);
        verify(spyBoard, times(1)).setPositionValue(6, TicTacToeSymbol.O);
    }

    @Test
    public void testGetSymbol(){
        assertEquals(reader.getSymbol("X"), TicTacToeSymbol.X);
        assertEquals(reader.getSymbol("O"), TicTacToeSymbol.O);
        assertNull(reader.getSymbol("H"));
    }
}
