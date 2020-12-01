package nl.codechallenge.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {
    @CsvBindByName
    private Integer CustomerId;
    @CsvBindByName
    private String Name;
    @CsvBindByName
    private Country Country;
}
