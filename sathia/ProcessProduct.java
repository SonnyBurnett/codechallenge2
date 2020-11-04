import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.ListIterator;

public class ProcessProduct implements ProductInterface{
    public static void main(String[] args) {
        ProductInterface obj = new ProcessProduct();
        ArrayList list = obj.readAndProcessFile();
        obj.writeToFile(list);
    }

    @Override
    public ArrayList readAndProcessFile() {
        ArrayList<Product> productlist = new ArrayList<Product>();
        try {
            Product product;
            String splitBy = ",";
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jk09cl\\Project\\001-experts-inputs.csv"));
            // To skip the header
            br.readLine();
            // Read the data
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] row = line.split(splitBy);
                Double price = Double.parseDouble(row[3]);
                // Process only the products which has price as less than 10.
                if(price >= 10) {
                    product = new Product();
                    product.setProductId(Integer.parseInt(row[0].trim()));
                    product.setName(row[1]);
                    product.setDescription(row[2]);
                    // Convert into $
                    product.setPrice(price*Product.conversionRate);
                    product.setCategory(row[4]);
                    productlist.add(product);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return productlist;
    }

    @Override
    public void writeToFile(ArrayList list) {
        try {
            FileWriter csvWriter = new FileWriter("C:\\Users\\jk09cl\\Project\\001-experts-output.csv");
            String[] header = {"Product ID","Name","Description","Price","Category"};
            // Write Header
            csvWriter.append(String.join(",",header[0],header[1],header[2],header[3],header[4]));
            csvWriter.append("\n");
            ListIterator<Product> listItr = list.listIterator();
            while(listItr.hasNext())
            {
                Product product = listItr.next();
                // Write Data
                csvWriter.append(String.join(",",product.getProductId().toString(),product.getName(),product.getDescription(),product.getPrice().toString(),product.getCategory()));
                csvWriter.append("\n");
            };

            csvWriter.flush();
            csvWriter.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
