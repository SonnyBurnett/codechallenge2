import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFactory {
    public Scanner createScanner(File file) throws FileNotFoundException {
        return new Scanner(file);
    }
}
