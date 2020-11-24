package model;

import model.board.TicTacToeBoard;
import model.TicTacToeGame;
import org.junit.jupiter.api.Test;
import model.TicTacToePlayer;
import model.TicTacToeSymbol;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicTacToeGameTest {

    private final TicTacToePlayer mockPlayer1 = mock(TicTacToePlayer.class);
    private final TicTacToePlayer mockPlayer2 = mock(TicTacToePlayer.class);
    {
        when(mockPlayer1.getSymbol()).thenReturn(TicTacToeSymbol.X);
        when(mockPlayer2.getSymbol()).thenReturn(TicTacToeSymbol.O);
    }

    private final TicTacToeBoard mockBoard = mock(TicTacToeBoard.class);

    private final TicTacToeGame game = new TicTacToeGame(mockBoard, mockPlayer1, mockPlayer2);
    private final TicTacToeGame spyGame = spy(game);

    @Test
    public void testDetermineNextPlayerEmptyBoard() {
        // Assign
        TicTacToeGame newGame = new TicTacToeGame(mockBoard, mockPlayer1, mockPlayer2);
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(0);

        // Act + assert
        assertEquals(TicTacToeSymbol.X, newGame.determineNextPlayer().getSymbol());
    }

    @Test
    public void testDetermineNextPlayerNonEmptyBoardEven() {
        // Assign
        TicTacToeGame newGame = new TicTacToeGame(mockBoard, mockPlayer1, mockPlayer2);
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(2);

        // Act + assert
        assertEquals(TicTacToeSymbol.X, newGame.determineNextPlayer().getSymbol());
    }

    @Test
    public void testDetermineNextPlayerNonEmptyBoardUneven() {
        // Assign
        TicTacToeGame newGame = new TicTacToeGame(mockBoard, mockPlayer1, mockPlayer2);
        when(mockBoard.getNumberOccupiedPositions()).thenReturn(3);

        // Act + assert
        assertEquals(TicTacToeSymbol.O, newGame.determineNextPlayer().getSymbol());
    }

    @Test
    public void testHasWinner() {
        // Assign
        TicTacToeSymbol value = TicTacToeSymbol.X;
        when(mockBoard.hasThreeInRow()).thenReturn(value.toString());

        // Act + Assert
        assertEquals(mockPlayer1, game.hasWinner());
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
        TicTacToeSymbol value = TicTacToeSymbol.X;
        doReturn(mockPlayer1).when(spyGame).hasWinner();
        when(mockPlayer1.getSymbol()).thenReturn(value);

        // Act + assert
        assertEquals("WINNER " + value, spyGame.nextTurn());
    }

    @Test
    public void testNextTurnHasNoWinner() {
        // Assign
        TicTacToeSymbol value = TicTacToeSymbol.X;
        doReturn(null).when(spyGame).hasWinner();
        when(mockPlayer1.getSymbol()).thenReturn(value);

        // Act + assert
        assertEquals("NEXTMOVE " + value, spyGame.nextTurn());
    }
}
