import board.TicTacToeBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicTacToeGameTest {

    private final TicTacToeBoard mockBoard = mock(TicTacToeBoard.class);

    private final TicTacToeGame game = new TicTacToeGame(mockBoard);

    @Test
    public void testDetermineNextPlayerEmptyBoard() {
        // Assign
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(0);

        // Act + assert
        assertEquals("X", game.determineNextPlayer().getSymbol());
    }

    @Test
    public void testDetermineNextPlayerNonEmptyBoardEven() {
        // Assign
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(2);

        // Act + assert
        assertEquals("X", game.determineNextPlayer().getSymbol());
    }

    @Test
    public void testDetermineNextPlayerNonEmptyBoardUneven() {
        // Assign
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(3);

        // Act + assert
        assertEquals("O", game.determineNextPlayer().getSymbol());
    }

    @Test
    public void testHasWinner() {
        // Assign
        String value = "X";
        when(mockBoard.threeInRow()).thenReturn(value);

        // Act + Assert
        assertTrue(game.hasWinner());
    }

    @Test
    public void testHasWinnerNone() {
        // Assign
        when(mockBoard.threeInRow()).thenReturn(null);

        // Act + Assert
        assertFalse(game.hasWinner());
    }
}
