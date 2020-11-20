package nl.codechallenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringBootTest(args = "file")
class ApplicationTest {

    @MockBean
    CommandLineRunner commandLineRunner;

    @Test
    void applicationRunsCommandLineRunner() throws Exception {
        verify(commandLineRunner, times(1)).run("file");
    }

}
