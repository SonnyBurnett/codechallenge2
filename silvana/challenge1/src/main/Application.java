package main;

import main.filehandlers.CsvFileHandler;
import main.filehandlers.ProductCsvConvertor;
import main.models.ECurrency;
import main.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args){
        String inputFilePath = "./src/resources/inputs.csv";
        String outputFilePath = "./src/resources/outputs.csv";

        CsvFileHandler csvFileHandler =  new CsvFileHandler(",");

        if(csvFileHandler.doesFileExist(inputFilePath)){
            List<List<String>> inputContents = csvFileHandler.readFileContent(inputFilePath);
            List<String> headers = inputContents.get(0);
            inputContents.remove(0);

            ProductCsvConvertor productCsvHandler = new ProductCsvConvertor(headers);
            List<Product> inputProducts = productCsvHandler.stringArrayToProducts(inputContents);
            if(inputProducts.size() > 0){
                List<Product> outputProducts = new ArrayList<>();
                for(Product p : inputProducts){
                    if(p.getPrice() > 10){
                        p.setCurrency(ECurrency.EUR);
                        outputProducts.add(p);
                    }
                }
                csvFileHandler.writeFileContent(outputFilePath, productCsvHandler.productsToStringArray(outputProducts));
            } else{
                System.out.println("There are no products loaded!");
            }
        }
        else{
            System.out.println("The file does not exist!");
        }

    }
}
