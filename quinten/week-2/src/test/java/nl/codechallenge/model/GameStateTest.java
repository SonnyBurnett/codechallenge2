package nl.codechallenge.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GameStateTest {
    @Test
    public void toStringGivesDescription() {
        assertThat(GameState.O_NEXT.toString(), equalTo("NEXTMOVE O"));
        assertThat(GameState.X_NEXT.toString(), equalTo("NEXTMOVE X"));
        assertThat(GameState.O_WIN.toString(), equalTo("WINNER O"));
        assertThat(GameState.X_WIN.toString(), equalTo("WINNER X"));
    }
}
