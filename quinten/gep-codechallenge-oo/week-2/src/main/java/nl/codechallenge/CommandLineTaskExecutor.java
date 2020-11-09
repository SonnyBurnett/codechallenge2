package nl.codechallenge;

import nl.codechallenge.io.OutputWriter;
import nl.codechallenge.io.TicTactToeFileReader;
import nl.codechallenge.model.Game;
import nl.codechallenge.model.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Profile("!test")
@Component
public class CommandLineTaskExecutor implements CommandLineRunner {

    private final ArgumentValidator argumentValidator;
    private final TicTactToeFileReader ticTactToeFileReader;
    private final OutputWriter outputWriter;

    @Autowired
    public CommandLineTaskExecutor(ArgumentValidator argumentValidator, TicTactToeFileReader ticTactToeFileReader,
                                   OutputWriter outputWriter) {
        this.argumentValidator = argumentValidator;
        this.ticTactToeFileReader = ticTactToeFileReader;
        this.outputWriter = outputWriter;
    }

    @Override
    public void run(String... args) {
        argumentValidator.validate(Arrays.asList(args));

        Game game;

        try {
            Path path = Paths.get(args[0]);
            game = ticTactToeFileReader.read(path);
        } catch (IOException e) {
            throw new RuntimeException("Problem reading input file: " + args[0]);
        }

        GameState state = game.state();

        try {
            outputWriter.write(state.toString());
        } catch (IOException e) {
            throw new RuntimeException("Problem writing answer to file.");
        }
    }

}
