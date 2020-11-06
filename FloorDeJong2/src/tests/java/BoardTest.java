import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

//    private final Board board = new Board();

    @Test
    public void testCreateEmptyBoard() {
        // Act
        Board board = new Board();

        // Assert
        assertEquals(3, board.getBoard().size());
        for(int i=3 ; i<3; i++) {
            assertEquals(3, board.getBoard().get(i).size());
            assertEquals(new ArrayList<String>(3), board.getBoard().get(i));
        }
    }

    @Test
    public void testCreateCorrectSize() {
        // Assign
        List<List<String>> inputBoard = new ArrayList<>();
        Random rand = new Random();
        List<String> givenList = Arrays.asList(".", "X", "O");

        for (int i = 0; i < 3; i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                int randomIndex = rand.nextInt(givenList.size());
                tmp.add(givenList.get(randomIndex));
            }
            inputBoard.add(tmp);
        }

        // Act
        Board board = new Board(inputBoard);

        // Assert
        assertEquals(inputBoard, board.getBoard());
    }

    @Test
    public void testCreateIncorrectSizeXDirection() {
        // Assign
        List<List<String>> inputBoard = new ArrayList<>();
        Random rand = new Random();
        List<String> givenList = Arrays.asList(".", "X", "O");

        for (int i = 0; i < 4; i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                int randomIndex = rand.nextInt(givenList.size());
                tmp.add(givenList.get(randomIndex));
            }
            inputBoard.add(tmp);
        }

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Board(inputBoard);
        });

        String expectedMessage = "The sizes of given board is incorrect.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateIncorrectSizeYDirection() {
        // Assign
        List<List<String>> inputBoard = new ArrayList<>();
        Random rand = new Random();
        List<String> givenList = Arrays.asList(".", "X", "O");

        for (int i = 0; i < 3; i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                int randomIndex = rand.nextInt(givenList.size());
                tmp.add(givenList.get(randomIndex));
            }
            inputBoard.add(tmp);
        }

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Board(inputBoard);
        });

        String expectedMessage = "The sizes of given board is incorrect.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
