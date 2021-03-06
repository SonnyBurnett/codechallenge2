package model.board;

import Exceptions.IllegalMoveException;
import org.junit.jupiter.api.Test;
import model.TicTacToeSymbol;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TicTacToeBoardTest {

    private final BoardPosition mockPosition = mock(BoardPosition.class);

    private final BoardPositionFactory mockBoardPositionFactory = mock(BoardPositionFactory.class);
    { when(mockBoardPositionFactory.createBoardPosition(anyInt())).thenReturn(mockPosition); }

    private final TicTacToeBoard board = new TicTacToeBoard(mockBoardPositionFactory);
    private final TicTacToeBoard spyBoard = spy(board);

    @Test
    public void testCreate() {
        // Act
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        // Assert
        assertEquals(9, ticTacToeBoard.getPositions().size());
    }

    @Test
    public void testSetPositionValueHappyFlow() {
        // Assign
        TicTacToeSymbol value =TicTacToeSymbol.X;
        int positionNr = 1;
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        // Act
        ticTacToeBoard.setPositionValue(positionNr, value);

        // Assert
        assertEquals(value.toString(), ticTacToeBoard.getPositions().get(positionNr).getValue());

        for (int i = 0; i< ticTacToeBoard.getPositions().size(); i++) {
            if (i != positionNr) {
                assertNull(ticTacToeBoard.getPositions().get(i).getValue());
            }
        }
    }

    @Test
    public void testSetPositionValueIncorrectPositionNr() {
        // Assign
        TicTacToeSymbol value =TicTacToeSymbol.X;
        int positionNr = 9;

        doThrow(new IllegalArgumentException("Incorrect position number. Expected: 0 - 8, received: " + positionNr))
                .when(spyBoard).isExistingPosition(anyInt());
//        doReturn(true).when(spyBoard).isCorrectValue(anyString());
        when(mockPosition.isOccupied()).thenReturn(false);

        // Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> spyBoard.setPositionValue(positionNr, value));

        String expectedMessage = "Incorrect position number. Expected: 0 - 8, received: " + positionNr;
        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    public void testSetPositionValueOccupiedPosition() {
        // Assign
        TicTacToeSymbol value =TicTacToeSymbol.X;
        int positionNr = 1;

        doReturn(true).when(spyBoard).isExistingPosition(anyInt());
        when(mockPosition.isOccupied()).thenReturn(true);

        // Act + assert
        Exception exception = assertThrows(IllegalMoveException.class, () -> spyBoard.setPositionValue(positionNr, value));

        String expectedMessage = "Position " + positionNr + " is already occupied";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testGetNumberOccupiedPositionsEmptyBoard() {
        // Assign
        when(mockPosition.isOccupied()).thenReturn(false);

        // Act + assert
        assertEquals(0, spyBoard.getNumberOccupiedPositions());
    }

    @Test
    public void testGetNumberOccupiedPositionsNonEmptyBoard() {
        // Assign
        when(mockPosition.isOccupied()).thenReturn(true).thenReturn(false);


        // Act + assert
        assertEquals(1, board.getNumberOccupiedPositions());
    }

    @Test
    public void testThreeInRowInDirectionTrue() {
        // Assign
        when(mockPosition.getNeighbourAtDirection(anyString())).thenReturn(mockPosition);
        when(mockPosition.sameValue(any(BoardPosition.class))).thenReturn(true);

        // Act + assert
        assertTrue(spyBoard.hasThreeInRow(mockPosition, "L"));
    }

    @Test
    public void testThreeInRowInDirectionFalse() {
        // Assign
        when(mockPosition.getNeighbourAtDirection(anyString())).thenReturn(mockPosition);
        when(mockPosition.sameValue(any(BoardPosition.class))).thenReturn(true).thenReturn(false);

        // Act + assert
        assertFalse(spyBoard.hasThreeInRow(mockPosition, "L"));
    }

    @Test
    public void testThreeInRowOnSides(){
        // Assign
        String value = "X";
        when(mockPosition.getValue()).thenReturn(value);

        doReturn(true).doReturn(false).when(spyBoard).hasThreeInRow(any(BoardPosition.class), anyString());

        // Act + assert
        assertEquals(value, spyBoard.hasThreeInRowOnSides());
        assertNull(spyBoard.hasThreeInRowOnSides());
    }

    @Test
    public void testThreeInRowMiddlePosition(){
        // Assign
        String value = "X";
        when(mockPosition.getValue()).thenReturn(value);

        doReturn(true).doReturn(false).when(spyBoard).hasThreeInRow(any(BoardPosition.class), anyString());

        // Act + assert
        assertEquals(value, spyBoard.hasThreeInRowMiddlePosition());
        assertNull(spyBoard.hasThreeInRowMiddlePosition());
    }

    @Test
    public void testThreeInRowViaMiddle() {
        // Assign
        String value = "X";
        doReturn(value).when(spyBoard).hasThreeInRowMiddlePosition();
        doReturn(null).when(spyBoard).hasThreeInRowOnSides();

        // Act + assert
        assertEquals(value, spyBoard.hasThreeInRow());
    }

    @Test
    public void testThreeInRowViaSide() {
        // Assign
        String value = "X";
        doReturn(null).when(spyBoard).hasThreeInRowMiddlePosition();
        doReturn(value).when(spyBoard).hasThreeInRowOnSides();

        // Act + assert
        assertEquals(value, spyBoard.hasThreeInRow());
    }

    @Test
    public void testThreeInRowNone() {
        // Assign
        doReturn(null).when(spyBoard).hasThreeInRowMiddlePosition();
        doReturn(null).when(spyBoard).hasThreeInRowOnSides();

        // Act + assert
        assertNull(spyBoard.hasThreeInRow());
    }

    @Test
    public void testTwoInRowInDirectionTrue() {
        // Assign
        TicTacToeSymbol value = TicTacToeSymbol.X;
        when(mockPosition.getNeighbourAtDirection(anyString())).thenReturn(mockPosition);
        when(mockPosition.getValue()).thenReturn(null).thenReturn(value.toString());
        when(mockPosition.sameValue(any(BoardPosition.class))).thenReturn(true);

        // Act + assert
        assertEquals(mockPosition, spyBoard.gettingWinningPosition(mockPosition, "L", value));
    }

    @Test
    public void testTwoInRowInDirectionNone() {
        // Assign
        when(mockPosition.getNeighbourAtDirection(anyString())).thenReturn(mockPosition);
        when(mockPosition.getValue()).thenReturn(null);
        when(mockPosition.sameValue(any(BoardPosition.class))).thenReturn(false);

        // Act + assert
        assertNull(spyBoard.gettingWinningPosition(mockPosition, "L", TicTacToeSymbol.X));
    }

    @Test
    public void testTwoInRowOnSides(){
        // Assign
        TicTacToeSymbol value = TicTacToeSymbol.X;
        when(mockPosition.getValue()).thenReturn(value.toString());

        doReturn(mockPosition).doReturn(null).when(spyBoard).getWinningInRowOnSides(any());

        // Act + assert
        assertEquals(mockPosition, spyBoard.getWinningInRowOnSides(value));
        assertNull(spyBoard.getWinningInRowOnSides(value));
    }

    @Test
    public void testTwoInRowMiddlePosition(){
        // Assign
        TicTacToeSymbol value = TicTacToeSymbol.X;
        when(mockPosition.getValue()).thenReturn(value.toString());

        doReturn(mockPosition).doReturn(null).when(spyBoard).getWinningPositionMiddlePosition(any());

        // Act + assert
        assertEquals(mockPosition, spyBoard.getWinningPositionMiddlePosition(value));
        assertNull(spyBoard.getWinningPositionMiddlePosition(value));
    }

    @Test
    public void testTwoInRowViaMiddle() {
        // Assign
        doReturn(mockPosition).when(spyBoard).getWinningPositionMiddlePosition(any());
        doReturn(null).when(spyBoard).getWinningInRowOnSides(any());

        // Act + assert
        assertEquals(mockPosition, spyBoard.gettingWinningPosition(TicTacToeSymbol.X));
    }

    @Test
    public void testTwoInRowViaSide() {
        // Assign
        String value = "X";
        doReturn(null).when(spyBoard).getWinningPositionMiddlePosition(any());
        doReturn(mockPosition).when(spyBoard).getWinningInRowOnSides(any());

        // Act + assert
        assertEquals(mockPosition, spyBoard.gettingWinningPosition(TicTacToeSymbol.X));
    }

    @Test
    public void testTwoInRowNone() {
        // Assign
        doReturn(null).when(spyBoard).getWinningPositionMiddlePosition(any());
        doReturn(null).when(spyBoard).getWinningInRowOnSides(any());

        // Act + assert
        assertNull(spyBoard.gettingWinningPosition(TicTacToeSymbol.X));
    }
}