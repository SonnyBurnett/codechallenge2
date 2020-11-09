package com.ing.challenge;

import com.ing.challenge.io.CsvWriter;
import com.ing.challenge.io.GameWriter;
import com.ing.challenge.model.board.Board;
import com.ing.challenge.model.board.TicTacToeBoard;
import com.ing.challenge.model.player.TTTPlayer;
import com.ing.challenge.model.player.TTTPlayerType;
import com.ing.challenge.model.rules.Rules;
import com.ing.challenge.model.rules.TicTacToeRules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

public class GameApp {
    private static final Logger logger = LogManager.getLogger(GameApp.class);
    TTTPlayer x = new TTTPlayer("Me", TTTPlayerType.X), o = new TTTPlayer("You", TTTPlayerType.O);

    public GameApp(TTTPlayer x, TTTPlayer o) {
        withPlayers(x, o);
    }

    public void playGameFromFile(File file) throws IOException {
        logger.info(() -> "Let the games begin!");
        Board board = new TicTacToeBoard(file);
        Rules rules = new TicTacToeRules();
        GameWriter<Iterable<?>> csvWriter = new CsvWriter();
        File output = pathToOutputFile("001-output.csv").toFile();
        Optional<TTTPlayerType> optionalWinner = rules.isWinner(board);
        optionalWinner.ifPresentOrElse(winner -> {
                    var player = getPlayer(winner);
                    logger.info(() -> (player + " is the winner!"));
                    csvWriter.write(output, player.toCsv("the winner is"));
                },
                () -> {
                    var player = getPlayer(rules.whoIsNext(board));
                    logger.info(() -> (player + " is next!"));
                    csvWriter.write(output, player.toCsv("next turn for"));
                });

    }

    protected TTTPlayer getPlayer(TTTPlayerType type) {
        return switch (type) {
            case X -> this.x;
            case O -> this.o;
        };
    }

    protected Path pathToOutputFile(String fileName) throws IOException {
        try {
            var source = Paths.get(GameApp.class.getResource("/").getPath(), fileName);
            if (Files.exists(source)) {
                Files.delete(source);
            }
            return source;
        } catch (IOException e) {
            logger.error(() -> "Failed to open file", e);
            throw e;
        }
    }

    private void withPlayers(TTTPlayer x, TTTPlayer o) {
        this.x = Objects.requireNonNull(x, "Player X can not be null");
        this.o = Objects.requireNonNull(o, "Player O can not be null");
    }

    public static void main(String[] args) {
        GameApp game = new GameApp(
                new TTTPlayer("Steve", TTTPlayerType.X, "Likes coffee", "does not like pickles"),
                new TTTPlayer("Jan", TTTPlayerType.O, "Likes knitting", "Vegetarian", "Has a cat..")
        );

        try {
            game.playGameFromFile(Path.of(ClassLoader.getSystemResource("002-experts.txt").toURI()).toFile());
        } catch (URISyntaxException | IOException e) {
            logger.error(() -> "Couldn't play the game due to errors", e);
        }
    }
}
