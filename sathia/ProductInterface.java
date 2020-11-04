import java.util.ArrayList;

public interface ProductInterface {
    public void writeToFile(ArrayList<Product> list);
    public ArrayList<Product> readAndProcessFile();
}
