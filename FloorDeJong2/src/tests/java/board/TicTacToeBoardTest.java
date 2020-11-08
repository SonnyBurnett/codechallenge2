package board;

import Exceptions.IllegalMoveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeBoardTest {

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
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        // Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ticTacToeBoard.setPositionValue(positionNr, value));

        String expectedMessage = "Incorrect position number. Expected: 0 - 8, received: " + positionNr;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testSetPositionValueIncorrectValue() {
        // Assign
        String value = "H";
        int positionNr = 1;
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        // Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ticTacToeBoard.setPositionValue(positionNr, value));

        String expectedMessage = "Incorrect symbol. Expected: one of " + ticTacToeBoard.getAllowedValues() + ", received: " + value;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testSetPositionValueOccupiedPosition() {
        // Assign
        String value = "X";
        int positionNr = 1;
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        ticTacToeBoard.setPositionValue(positionNr, "O");

        // Act + assert
        Exception exception = assertThrows(IllegalMoveException.class, () -> ticTacToeBoard.setPositionValue(positionNr, value));

        String expectedMessage = "Position " + positionNr + " is already occupied";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testGetNumberOccupiedPositionsEmptyBoard() {
        // Assign
        TicTacToeBoard board = new TicTacToeBoard();

        // Act + assert
        assertEquals(0, board.getNumberOccupiedPositions());
    }

    @Test
    public void testGetNumberOccupiedPositionsNonEmptyBoard() {
        // Assign
        TicTacToeBoard board = new TicTacToeBoard();
        board.setPositionValue(1, "X");
        board.setPositionValue(8, "O");


        // Act + assert
        assertEquals(2, board.getNumberOccupiedPositions());
    }
}