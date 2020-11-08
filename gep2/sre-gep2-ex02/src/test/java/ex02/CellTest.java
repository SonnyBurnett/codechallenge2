package ex02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellTest {
    private Cell test_Cell;

    @BeforeEach
    void setUp() {
        test_Cell = new Cell();
    }

    @Test
    void test_getCellValue() {
        assertEquals(".", test_Cell.getCellValue());
    }

    @Test
    void test_setCellValue() {
        test_Cell.setCellValue(Player.X.toString());
        assertEquals("X", test_Cell.getCellValue());
        test_Cell.setCellValue(Player.O.toString());
        assertEquals("O", test_Cell.getCellValue());
        test_Cell.setCellValue(Player.NONE.toString());
        assertEquals("NONE", test_Cell.getCellValue());
    }
}