package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest {
    private CSVReader reader;

    @BeforeEach
    void setup() {
        this.reader = new CSVReader("test.csv");
    }

    @DisplayName("should throw IllegalStateException  if path has length of 0")
    @Test
    void setLines() {
        assertThrows(IllegalStateException.class, () -> {
            CSVReader readerWithNoPath = new CSVReader("");
            readerWithNoPath.setLines();
        });
    }

    @DisplayName("should return a csv formatted string")
    @Test
    void formatLines() {
        String formatted = this.reader.formatLine(new String[]{"a","b", "c"});
        assertEquals("a, b, c", formatted);
    }
}
