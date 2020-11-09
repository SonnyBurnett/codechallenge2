package com.ing.challenge;

import com.ing.challenge.model.player.TTTPlayer;
import com.ing.challenge.model.player.TTTPlayerType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.google.common.truth.Truth.assertThat;

public class GameAppTest {
    private final TTTPlayer playerX = new TTTPlayer("test", TTTPlayerType.X);
    private final TTTPlayer playerO = new TTTPlayer("foe", TTTPlayerType.O);

    @Test
    void playersCanNotBeNull() {
        var playerXErr = Assertions.assertThrows(NullPointerException.class,
                () -> new GameApp(null, null));
        var playerOErr = Assertions.assertThrows(NullPointerException.class,
                () -> new GameApp(new TTTPlayer("test", TTTPlayerType.X), null));
        assertThat(playerXErr).hasMessageThat().isEqualTo("Player X can not be null");
        assertThat(playerOErr).hasMessageThat().isEqualTo("Player O can not be null");
    }

    @Test
    void createGameWithPlayers() {
        var game = new GameApp(playerX, playerO);
        assertThat(game != null).isTrue();
    }

    @Test
    void pathToOutput() throws IOException {
        var fileName = "999-creation.csv";
        var pathToFile = new GameApp(playerX, playerO).pathToOutputFile(fileName);
        assertThat(pathToFile.toFile().createNewFile()).isTrue();
        assertThat(Paths.get(GameApp.class.getResource("/").getPath(), fileName).toFile().exists()).isTrue();
    }

    @Test
    void getAPlayer() {
        var game = new GameApp(playerX, playerO);
        assertThat(game.getPlayer(TTTPlayerType.O)).isInstanceOf(TTTPlayer.class);
        assertThat(game.getPlayer(TTTPlayerType.O).name()).isEqualTo("foe");
    }

    @ParameterizedTest
    @ValueSource(strings = {"002-experts.txt", "003-O-wins.txt", "004-X-wins.txt", "005-X-wins.txt"})
    void runGames(String fileName) throws URISyntaxException, IOException {
        Path path = Path.of(getClass().getResource("/").toURI());
        assertThat(path.toFile().list()).asList().doesNotContain(fileName);
        var game = new GameApp(playerX,playerO);
        File file = Paths.get("src/test/resources",fileName).toFile();
        game.playGameFromFile(file);
    }

}
