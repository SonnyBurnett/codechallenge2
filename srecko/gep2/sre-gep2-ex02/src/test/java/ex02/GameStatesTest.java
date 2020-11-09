package ex02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class GameStatesTest {

    @Test
    void test_winConditions() {
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 3, 6),
                Arrays.asList(1, 4, 7),
                Arrays.asList(2, 5, 8),
                Arrays.asList(0, 1, 2),
                Arrays.asList(3, 4, 5),
                Arrays.asList(6, 7, 8),
                Arrays.asList(0, 4, 8),
                Arrays.asList(2, 4, 6)
        );

        List<List<Integer>> actual = GameStates.winConditions();

        assertEquals(expected, actual);
    }
}