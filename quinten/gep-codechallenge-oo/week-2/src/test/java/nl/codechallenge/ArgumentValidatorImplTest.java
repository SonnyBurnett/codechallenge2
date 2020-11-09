package nl.codechallenge;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentValidatorImplTest {

    ArgumentValidator validator = new ArgumentValidatorImpl();

    @Test
    void noArguments() {
        assertProblem(validator, Collections.emptyList());
    }

    @Test
    void nullArgument() {
        assertProblem(validator, null);
    }

    @Test
    void tooManyArguments() {
        assertProblem(validator, Arrays.asList("one", "two"));
    }

    @Test
    void happyFlow() {
        validator.validate(Arrays.asList("one"));
        // no exception
    }

    private void assertProblem(ArgumentValidator validator, List<String> args) {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            validator.validate(args);
        });

        assertThat(exception.getMessage(),
                Matchers.equalTo("Please provide a single argument, being the input file " + "path"));
    }

}
