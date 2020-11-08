package ex02;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        String testFileStr = "src/test/resources/002-experts.txt";
        File testFile = new File(testFileStr);
        if (!testFile.exists()) {
            testFileStr = "gep2/sre-gep2-ex02/src/test/resources/002-experts.txt";
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

    @Test
    void test_run() {
        Game ticTacToe = new TicTacToe();
        String testFileStr = "src/test/resources/002-experts-example.txt";
        String testFileStrOut = "src/test/resources/002-experts-example-output.csv";
        File testFile = new File(testFileStr);
        if (!testFile.exists()) {
            testFileStr = "gep2/sre-gep2-ex02/src/test/resources/002-experts-example.txt";
            testFileStrOut = "gep2/sre-gep2-ex02/src/test/resources/002-experts-example-output.csv";
        }
        /* TODO: fix IntelliJ, JUnit and Maven resources path conflicts for read resource paths. */


        try {
            ticTacToe.load(testFileStr);
            ticTacToe.play();
            ticTacToe.write(testFileStrOut);
        } catch (Exception exception) {
            System.out.println("No load! ("+exception.toString()+")");
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(testFileStrOut));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String actualLine = "";

        if (scanner.hasNextLine()) {
            actualLine = scanner.nextLine();
       };
        scanner.close();

        assertEquals("WINNER X",actualLine);
    }
}