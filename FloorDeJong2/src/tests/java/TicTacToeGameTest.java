import board.TicTacToeBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicTacToeGameTest {

    private final TicTacToePlayer mockPlayer = mock(TicTacToePlayer.class);

    private final TicTacToePlayerFactory mockPlayerFactory = mock(TicTacToePlayerFactory.class);
    { when(mockPlayerFactory.createPlayer(anyInt(), anyString(), anyString())).thenReturn(mockPlayer); }

    private final TicTacToeBoard mockBoard = mock(TicTacToeBoard.class);

    private final TicTacToeGame game = new TicTacToeGame(mockBoard, mockPlayerFactory);
    private final TicTacToeGame spyGame = spy(game);

    @Test
    public void testDetermineNextPlayerEmptyBoard() {
        // Assign
        TicTacToeGame newGame = new TicTacToeGame(mockBoard);
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(0);

        // Act + assert
        assertEquals("X", newGame.determineNextPlayer().getSymbol());
    }

    @Test
    public void testDetermineNextPlayerNonEmptyBoardEven() {
        // Assign
        TicTacToeGame newGame = new TicTacToeGame(mockBoard);
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(2);

        // Act + assert
        assertEquals("X", newGame.determineNextPlayer().getSymbol());
    }

    @Test
    public void testDetermineNextPlayerNonEmptyBoardUneven() {
        // Assign
        TicTacToeGame newGame = new TicTacToeGame(mockBoard);
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(3);

        // Act + assert
        assertEquals("O", newGame.determineNextPlayer().getSymbol());
    }

    @Test
    public void testHasWinner() {
        // Assign
        String value = "X";
        when(mockBoard.hasThreeInRow()).thenReturn(value);

        // Act + Assert
        assertEquals(mockPlayer, game.hasWinner());
    }

    @Test
    public void testHasWinnerNone() {
        // Assign
        when(mockBoard.hasThreeInRow()).thenReturn(null);

        // Act + Assert
        assertNull(game.hasWinner());
    }

    @Test
    public void testNextTurnHasWinner() {
        // Assign
        String value = "X";
        doReturn(mockPlayer).when(spyGame).hasWinner();
        when(mockPlayer.getSymbol()).thenReturn(value);

        // Act + assert
        assertEquals("WINNER " + value, spyGame.nextTurn());
    }

    @Test
    public void testNextTurnHasNoWinner() {
        // Assign
        String value = "X";
        doReturn(null).when(spyGame).hasWinner();
        when(mockPlayer.getSymbol()).thenReturn(value);

        // Act + assert
        assertEquals("NEXTMOVE " + value, spyGame.nextTurn());
    }
}
