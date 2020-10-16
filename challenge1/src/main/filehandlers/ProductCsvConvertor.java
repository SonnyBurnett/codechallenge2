package main.filehandlers;

import main.models.ECategory;
import main.models.ECurrency;
import main.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class ProductCsvConvertor {
    private List<String>  headers;

    public ProductCsvConvertor(List<String> headers) {
        this.headers = headers;
    }

    public List<List<String>> productsToStringArray(List<Product> products) {
        List<List<String>> records = new ArrayList<>();
        records.add(this.headers);
        Iterator<Product> it = products.iterator();
        while(it.hasNext()){
            Product product = it.next();
            records.add(Arrays.asList(product.getId(),product.getName(),product.getDescription(),product.getPrice().toString(),product.getCategory().toString()));
        }
        return records;
    }

    public List<Product> stringArrayToProducts (List<List<String>> records) {
        List<Product> products = new ArrayList<>();
        try{
            Iterator<List<String>> it = records.iterator();
            while(it.hasNext()) {
                List<String> line = it.next();
                products.add(new Product(line.get(0), line.get(1), line.get(2), Double.parseDouble(line.get(3)), ECategory.valueOf(line.get(4)), ECurrency.USD));

            }
        }catch(Exception e){
            System.out.println("Error while loading products: "+ e.getMessage());
        }finally{
            return products;
        }

    }
}
