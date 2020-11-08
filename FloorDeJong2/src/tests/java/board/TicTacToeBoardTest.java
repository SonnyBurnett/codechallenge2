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
        assertTrue(spyBoard.threeInRowInDirection(mockPosition, "L"));
    }

    @Test
    public void testThreeInRowInDirectionFalse() {
        // Assign
        when(mockPosition.getNeighbourAtDirection(anyString())).thenReturn(mockPosition);
        when(mockPosition.sameValue(any(BoardPosition.class))).thenReturn(true).thenReturn(false);

        // Act + assert
        assertFalse(spyBoard.threeInRowInDirection(mockPosition, "L"));
    }

    @Test
    public void testThreeInRowOnSides(){
        // Assign
        String value = "X";
        when(mockPosition.getValue()).thenReturn(value);

        doReturn(true).doReturn(false).when(spyBoard).threeInRowInDirection(any(BoardPosition.class), anyString());

        // Act + assert
        assertEquals(value, spyBoard.threeInRowOnSides());
        assertNull(spyBoard.threeInRowOnSides());
    }

    @Test
    public void testThreeInRowMiddlePosition(){
        // Assign
        String value = "X";
        when(mockPosition.getValue()).thenReturn(value);

        doReturn(true).doReturn(false).when(spyBoard).threeInRowInDirection(any(BoardPosition.class), anyString());

        // Act + assert
        assertEquals(value, spyBoard.threeInRowMiddlePosition());
        assertNull(spyBoard.threeInRowMiddlePosition());
    }

    @Test
    public void testThreeInRowMiddle() {
        // Assign
        String value = "X";
        doReturn(value).when(spyBoard).threeInRowMiddlePosition();
        doReturn(null).when(spyBoard).threeInRowOnSides();

        // Act + assert
        assertEquals(value, spyBoard.threeInRow());
    }

    @Test
    public void testThreeInRowSide() {
        // Assign
        String value = "X";
        doReturn(null).when(spyBoard).threeInRowMiddlePosition();
        doReturn(value).when(spyBoard).threeInRowOnSides();

        // Act + assert
        assertEquals(value, spyBoard.threeInRow());
    }

    @Test
    public void testThreeInRowNone() {
        // Assign
        doReturn(null).when(spyBoard).threeInRowMiddlePosition();
        doReturn(null).when(spyBoard).threeInRowOnSides();

        // Act + assert
        assertNull(spyBoard.threeInRow());
    }
}