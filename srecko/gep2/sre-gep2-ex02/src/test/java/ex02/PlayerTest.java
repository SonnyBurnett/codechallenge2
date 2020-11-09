package ex02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    @Test
    void test_PlayerTest() {
        assertEquals("X", Player.X.getPlayer());
        assertEquals("O", Player.O.getPlayer());
        assertEquals(".", Player.NONE.getPlayer());
    }
}