package nl.codechallenge.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderShipping {
    @CsvRecurse
    private CustomerInfo customerInfo;
    @CsvBindByName
    private nl.codechallenge.model.Shipper Shipper;
    @CsvBindByName
    private Integer Duration;
    @CsvBindByName
    private Double ShippingCost;
}
