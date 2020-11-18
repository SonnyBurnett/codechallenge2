package nl.codechallenge;

import nl.codechallenge.io.OutputWriter;
import nl.codechallenge.io.TicTactToeFileReader;
import nl.codechallenge.model.Game;
import nl.codechallenge.model.GameState;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CommandLineTaskExecutorTest {

    private ArgumentValidator validator;
    private TicTactToeFileReader reader;
    private OutputWriter writer;
    private CommandLineTaskExecutor executor;

    @BeforeEach
    void beforeEach() {
        validator = mock(ArgumentValidator.class);
        reader = mock(TicTactToeFileReader.class);
        writer = mock(OutputWriter.class);
        executor = new CommandLineTaskExecutor(validator, reader, writer);
    }

    @Test
    void runHappyFlow() throws IOException, URISyntaxException {
        // GIVEN
        Game game = mock(Game.class);
        when(game.state()).thenReturn(GameState.O_WIN);
        when(reader.read(any(Path.class))).thenReturn(game);

        // WHEN
        String arg1 = Paths.get("src/test/resources/empty_board.txt").toString();
        executor.run(arg1, "extraArg");

        // THEN
        assertValidation(validator);
        verify(reader, times(1)).read(Paths.get("src/test/resources/empty_board.txt"));
        verify(game, times(1)).state();
        verify(writer, times(1)).write(GameState.O_WIN.toString());
    }

    private void assertValidation(ArgumentValidator validator) {
        ArgumentCaptor<List<String>> args = ArgumentCaptor.forClass(List.class);
        verify(validator, times(1)).validate(args.capture());
        List<String> validationArgs = args.getValue();
        assertThat(validationArgs, hasSize(2));
        assertThat(validationArgs.get(0), Matchers.equalTo("src/test/resources/empty_board.txt"));
        assertThat(validationArgs.get(1), Matchers.equalTo("extraArg"));
    }

    @Test
    public void runWithWritingError() throws IOException {
        Game game = mock(Game.class);
        when(game.state()).thenReturn(GameState.O_WIN);
        when(reader.read(any(Path.class))).thenReturn(game);

        doThrow(new IOException()).when(writer).write(anyString());

        // when
        Exception exception = assertThrows(RuntimeException.class, () -> {
            executor.run("file");
        });

        assertThat(exception.getMessage(), Matchers.equalTo("Problem writing answer to file."));
    }

    @Test
    public void runWithReadingError() throws IOException {
        doThrow(new IOException()).when(reader).read(any(Path.class));

        // when
        Exception exception = assertThrows(RuntimeException.class, () -> {
            executor.run("file");
        });

        assertThat(exception.getMessage(), Matchers.equalTo("Problem reading input file: file"));
    }
}
