import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FilterProducts {
    private List<Product> inputList = new ArrayList<>();
    private List<Product> outputList;

    public FilterProducts(String file){
        readProductFile(file);
    }

    public void readProductFile(String file){
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(",");
                inputList.add(new Product(Integer.parseInt(data[0]), data[1], data[2], Double.parseDouble(data[3]), data[4]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<Product> getInputList() {
        return inputList;
    }

    public List<Product> getOutputList() {
        return outputList;
    }
}
