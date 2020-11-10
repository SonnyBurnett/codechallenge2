package com.ing.challenge;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.ing.challenge.io.CsvWriter;
import com.ing.challenge.model.player.TTTPlayer;
import com.ing.challenge.model.player.TTTPlayerType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
    @ValueSource(strings = {"002-experts.txt", "003-O-wins.txt", "004-X-wins.txt", "005-X-wins.txt", "006-O-is-next.txt"})
    void runGames(String fileName) throws URISyntaxException, IOException {
        Path path = Path.of(getClass().getResource("/").toURI());
        assertThat(path.toFile().list()).asList().doesNotContain(fileName);
        var game = new GameApp(playerX, playerO);
        File file = Paths.get("src/test/resources", fileName).toFile();
        game.playGameFromFile(file);
    }

    @Test
    void ioErrorWritingToFile() throws Exception {
        File file = Paths.get("src/test/resources", "file//failed.txt").toFile();
        var game = new GameApp(playerX, playerO);
        var writer = new CsvWriter();
        int exitCode = SystemLambda.catchSystemExit(() -> game.writeToFile(writer, file, playerO, "fail"));
        assertThat(exitCode).isEqualTo(1);
    }

    @Test
    void runMain() {
        Assertions.assertDoesNotThrow(() -> GameApp.main(new String[]{}));
        // Check doesn't work in gradle on commandline. Does work in IDE..
//        String message = SystemLambda.tapSystemOut(() -> GameApp.main(new String[]{}));
//        System.out.println(message);
//        assertThat(message).contains("Let the games begin!");
//        assertThat(message).contains("Name: Steve, type: X, attributes: [Likes coffee, does not like pickles] is next!");
    }

}
