package nl.codechallenge.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct {
    @CsvBindByName
    private Integer CustomerId;
    @CsvBindByName
    private String Name;
    @CsvBindByName
    private String Product;
    @CsvBindByName
    private Double Price;
    @CsvBindByName
    private Double Weight;
    @CsvBindByName
    private Country Country;
}
