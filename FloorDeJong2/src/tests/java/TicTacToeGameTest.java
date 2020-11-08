import board.TicTacToeBoard;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicTacToeGameTest {

    private final TicTacToeBoard mockBoard = mock(TicTacToeBoard.class);

    @Test
    public void testDetermineNextPlayerEmptyBoard() {
        // Assign
        TicTacToeGame game = new TicTacToeGame(mockBoard);
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(0);

        // Act + assert
        assertEquals("X", game.determineNextPlayer().getSymbol());
    }

    @Test
    public void testDetermineNextPlayerNonEmptyBoardEven() {
        // Assign
        TicTacToeGame game = new TicTacToeGame(mockBoard);
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(2);

        // Act + assert
        assertEquals("X", game.determineNextPlayer().getSymbol());
    }

    @Test
    public void testDetermineNextPlayerNonEmptyBoardUneven() {
        // Assign
        TicTacToeGame game = new TicTacToeGame(mockBoard);
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(3);

        // Act + assert
        assertEquals("O", game.determineNextPlayer().getSymbol());
    }
}
