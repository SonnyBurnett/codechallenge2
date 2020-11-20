import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Assignment3Test {

    private Assignment3 assignment3 = Mockito.spy(new Assignment3());

    @Test
    public void test_run() throws IOException {
        String inputFile = "FloorDeJong3/src/tests/resources/correctInput.csv";
        String outputFile = "FloorDeJong3/src/tests/resources/output.csv";
        String expectedOutputFile = "FloorDeJong3/src/tests/resources/expectedOutput.csv";

        assignment3.run(inputFile, outputFile);

        byte[] expectedOutput = Files.readAllBytes(Paths.get(expectedOutputFile));
        byte[] output = Files.readAllBytes(Paths.get(outputFile));

        assertTrue(Arrays.equals(output, expectedOutput));
    }

}
