package nl.codechallenge.model;

import org.junit.jupiter.api.Test;

import static nl.codechallenge.model.Field.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GameTest {
    @Test
    void exercise() {
        Game game = new Game(X, X, X, O, O, X, O, O, NONE);
        assertThat(game.state(), equalTo(GameState.X_WIN));
    }

    @Test
    void rowWinners() {
        assertThat(new Game(X, X, X, NONE, NONE, NONE, NONE, NONE, NONE).state(), equalTo(GameState.X_WIN));
        assertThat(new Game(NONE, NONE, NONE, X, X, X, NONE, NONE, NONE).state(), equalTo(GameState.X_WIN));
        assertThat(new Game(NONE, NONE, NONE, NONE, NONE, NONE, X, X, X).state(), equalTo(GameState.X_WIN));
        assertThat(new Game(O, O, O, NONE, NONE, NONE, NONE, NONE, NONE).state(), equalTo(GameState.O_WIN));
        assertThat(new Game(NONE, NONE, NONE, O, O, O, NONE, NONE, NONE).state(), equalTo(GameState.O_WIN));
        assertThat(new Game(NONE, NONE, NONE, NONE, NONE, NONE, O, O, O).state(), equalTo(GameState.O_WIN));
    }

    @Test
    void columnWinners() {
        assertThat(new Game(X, NONE, NONE, X, NONE, NONE, X, NONE, NONE).state(), equalTo(GameState.X_WIN));
        assertThat(new Game(NONE, X, NONE, NONE, X, NONE, NONE, X, NONE).state(), equalTo(GameState.X_WIN));
        assertThat(new Game(NONE, NONE, X, NONE, NONE, X, NONE, NONE, X).state(), equalTo(GameState.X_WIN));
        assertThat(new Game(O, NONE, NONE, O, NONE, NONE, O, NONE, NONE).state(), equalTo(GameState.O_WIN));
        assertThat(new Game(NONE, O, NONE, NONE, O, NONE, NONE, O, NONE).state(), equalTo(GameState.O_WIN));
        assertThat(new Game(NONE, NONE, O, NONE, NONE, O, NONE, NONE, O).state(), equalTo(GameState.O_WIN));
    }

    @Test
    void diagonalWinners() {
        assertThat(new Game(X, NONE, NONE, NONE, X, NONE, NONE, NONE, X).state(), equalTo(GameState.X_WIN));
        assertThat(new Game(NONE, NONE, X, NONE, X, NONE, X, NONE, NONE).state(), equalTo(GameState.X_WIN));
        assertThat(new Game(O, NONE, NONE, NONE, O, NONE, NONE, NONE, O).state(), equalTo(GameState.O_WIN));
        assertThat(new Game(NONE, NONE, O, NONE, O, NONE, O, NONE, NONE).state(), equalTo(GameState.O_WIN));
    }

    @Test
    void nextMove() {
        assertThat(new Game(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE).state(), equalTo(GameState.X_NEXT));

        assertThat(new Game(O, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE).state(), equalTo(GameState.X_NEXT));
        assertThat(new Game(X, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE).state(), equalTo(GameState.O_NEXT));

        assertThat(new Game(O, NONE, NONE, NONE, NONE, X, O, NONE, NONE).state(), equalTo(GameState.X_NEXT));
        assertThat(new Game(X, NONE, NONE, NONE, NONE, X, O, NONE, NONE).state(), equalTo(GameState.O_NEXT));
    }
}
