package board;

import Exceptions.IllegalMoveException;
import org.junit.jupiter.api.Test;

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
        String value = "X";
        int positionNr = 1;
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        // Act
        ticTacToeBoard.setPositionValue(positionNr, value);

        // Assert
        assertEquals(value, ticTacToeBoard.getPositions().get(positionNr).getValue());

        for (int i = 0; i< ticTacToeBoard.getPositions().size(); i++) {
            if (i != positionNr) {
                assertNull(ticTacToeBoard.getPositions().get(i).getValue());
            }
        }
    }

    @Test
    public void testSetPositionValueIncorrectPositionNr() {
        // Assign
        String value = "X";
        int positionNr = 9;

        doThrow(new IllegalArgumentException("Incorrect position number. Expected: 0 - 8, received: " + positionNr))
                .when(spyBoard).isExistingPosition(anyInt());
        doReturn(true).when(spyBoard).isCorrectValue(anyString());
        when(mockPosition.isOccupied()).thenReturn(false);

        // Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> spyBoard.setPositionValue(positionNr, value));

        String expectedMessage = "Incorrect position number. Expected: 0 - 8, received: " + positionNr;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testSetPositionValueIncorrectValue() {
        // Assign
        String value = "H";
        int positionNr = 1;

        doReturn(true).when(spyBoard).isExistingPosition(anyInt());
        doThrow(new IllegalArgumentException("Incorrect symbol. Expected: one of " + spyBoard.getAllowedValues() + ", received: " + value))
                .when(spyBoard).isCorrectValue(anyString());
        when(mockPosition.isOccupied()).thenReturn(false);


        // Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> spyBoard.setPositionValue(positionNr, value));

        String expectedMessage = "Incorrect symbol. Expected: one of " + spyBoard.getAllowedValues() + ", received: " + value;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testSetPositionValueOccupiedPosition() {
        // Assign
        String value = "X";
        int positionNr = 1;

        doReturn(true).when(spyBoard).isExistingPosition(anyInt());
        doReturn(true).when(spyBoard).isCorrectValue(anyString());
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
        assertTrue(spyBoard.hasThreeInRowInDirection(mockPosition, "L"));
    }

    @Test
    public void testThreeInRowInDirectionFalse() {
        // Assign
        when(mockPosition.getNeighbourAtDirection(anyString())).thenReturn(mockPosition);
        when(mockPosition.sameValue(any(BoardPosition.class))).thenReturn(true).thenReturn(false);

        // Act + assert
        assertFalse(spyBoard.hasThreeInRowInDirection(mockPosition, "L"));
    }

    @Test
    public void testThreeInRowOnSides(){
        // Assign
        String value = "X";
        when(mockPosition.getValue()).thenReturn(value);

        doReturn(true).doReturn(false).when(spyBoard).hasThreeInRowInDirection(any(BoardPosition.class), anyString());

        // Act + assert
        assertEquals(value, spyBoard.hasThreeInRowOnSides());
        assertNull(spyBoard.hasThreeInRowOnSides());
    }

    @Test
    public void testThreeInRowMiddlePosition(){
        // Assign
        String value = "X";
        when(mockPosition.getValue()).thenReturn(value);

        doReturn(true).doReturn(false).when(spyBoard).hasThreeInRowInDirection(any(BoardPosition.class), anyString());

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
        String value = "X";
        when(mockPosition.getNeighbourAtDirection(anyString())).thenReturn(mockPosition);
        when(mockPosition.getValue()).thenReturn(null).thenReturn(value);
        when(mockPosition.sameValue(any(BoardPosition.class))).thenReturn(true);

        // Act + assert
        assertEquals(mockPosition, spyBoard.createThreeInRowDirection(mockPosition, "L", value));
    }

    @Test
    public void testTwoInRowInDirectionNone() {
        // Assign
        when(mockPosition.getNeighbourAtDirection(anyString())).thenReturn(mockPosition);
        when(mockPosition.getValue()).thenReturn(null);
        when(mockPosition.sameValue(any(BoardPosition.class))).thenReturn(false);

        // Act + assert
        assertNull(spyBoard.createThreeInRowDirection(mockPosition, "L", "X"));
    }

    @Test
    public void testTwoInRowOnSides(){
        // Assign
        String value = "X";
        when(mockPosition.getValue()).thenReturn(value);

        doReturn(mockPosition).doReturn(null).when(spyBoard).hasTwoInRowOnSides(anyString());

        // Act + assert
        assertEquals(mockPosition, spyBoard.hasTwoInRowOnSides("X"));
        assertNull(spyBoard.hasTwoInRowOnSides("X"));
    }

    @Test
    public void testTwoInRowMiddlePosition(){
        // Assign
        String value = "X";
        when(mockPosition.getValue()).thenReturn(value);

        doReturn(mockPosition).doReturn(null).when(spyBoard).hasTwoInRowMiddlePosition(anyString());

        // Act + assert
        assertEquals(mockPosition, spyBoard.hasTwoInRowMiddlePosition("X"));
        assertNull(spyBoard.hasTwoInRowMiddlePosition("X"));
    }

    @Test
    public void testTwoInRowViaMiddle() {
        // Assign
        doReturn(mockPosition).when(spyBoard).hasTwoInRowMiddlePosition(anyString());
        doReturn(null).when(spyBoard).hasTwoInRowOnSides(anyString());

        // Act + assert
        assertEquals(mockPosition, spyBoard.hasTwoInRowForSymbol("X"));
    }

    @Test
    public void testTwoInRowViaSide() {
        // Assign
        String value = "X";
        doReturn(null).when(spyBoard).hasTwoInRowMiddlePosition(anyString());
        doReturn(mockPosition).when(spyBoard).hasTwoInRowOnSides(anyString());

        // Act + assert
        assertEquals(mockPosition, spyBoard.hasTwoInRowForSymbol("X"));
    }

    @Test
    public void testTwoInRowNone() {
        // Assign
        doReturn(null).when(spyBoard).hasTwoInRowMiddlePosition(anyString());
        doReturn(null).when(spyBoard).hasTwoInRowOnSides(anyString());

        // Act + assert
        assertNull(spyBoard.hasTwoInRowForSymbol("X"));
    }
}