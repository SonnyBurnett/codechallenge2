package nl.codechallenge.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct {
    @CsvRecurse
    private CustomerInfo customerInfo;
    @CsvBindByName
    private String Product;
    @CsvBindByName
    private Double Price;
    @CsvBindByName
    private Double Weight;
}
