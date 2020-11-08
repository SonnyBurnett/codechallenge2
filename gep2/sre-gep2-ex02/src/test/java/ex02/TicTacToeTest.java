package ex02;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {
    private Game ttt = new TicTacToe();

    @Test
    void test_loadException() {
        Exception exception = assertThrows(Exception.class, () -> ttt.load("gep2/sre-gep2-ex02/src/main/resources/NoFile.err"));

        String expectedMessage = " The board setup is not in order. Illegal game setup.";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void test_load() {
        /* Yes inline comments means something nasty;
         * IntelliJ, JUnit and Maven modular modules have a testing resources issue this is the workaround for now. */
        String testFileStr = "src/main/resources/002-experts.txt";
        File testFile = new File(testFileStr);
        if (!testFile.exists()) {
            testFileStr = "gep2/sre-gep2-ex02/src/main/resources/002-experts.txt";
        }
        /* TODO: fix IntelliJ, JUnit and Maven resources path conflicts for read resource paths. */
        try {
            ttt.load(testFileStr);
            assertEquals(Player.X, ttt.nextPlayer());
        } catch (Exception e) {
            assertEquals(" The board setup is not in order. Illegal game setup.", e.getMessage());
        }
    }

    @Test
    void test_write() {
        Exception exception = assertThrows(Exception.class, () -> ttt.write("gep1/NoFile.err"));

        String expectedMessage = " The board state is not written. Illegal game setup.";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }
}