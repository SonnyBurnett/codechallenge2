package ex02;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    private final Board board = new Board();

    @Test
    void print() {
        String test_expectedString =
                "-------\n" +
                        "|.|.|.|" +
                        "\n-------\n" +
                        "|.|.|.|" +
                        "\n-------\n" +
                        "|.|.|.|" +
                        "\n-------\n";

        ByteArrayOutputStream printOutPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutPut));
        board.print();

        assertEquals(test_expectedString, printOutPut.toString());
    }

    @Test
    void testToString() {
        assertEquals("012345678", board.toString("."));
    }
}