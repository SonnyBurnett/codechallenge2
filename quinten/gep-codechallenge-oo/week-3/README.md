# Week 3 coding challenge OO

Processing orders to shipping information.

## Usage

```lang=bash
mvn package && java -jar target/week3-1.0.jar $(pwd)/inputs.csv
```

## Exercise

Write a program that:

1. Reads all orders inputs.csv
1. Writes a file outputs.csv that prints the shipping information per customer using the following rules:
    1. Country = Netherlands and Weight < 10 Shipper PostNL, cost are 6,95
    1. Country = Belgium Shipper is BelgioPosto and cost are 1,95 + (1 * weight)
1. All other cases Shipper is DHL and cost are 12,95 + (1.5 * weight)
1. Duration for PostNL = 1, for BelgioPosto = 2
1. Duration for DHL
    1. (weight < 10) = 4
    1. (weight >= 10) = 8

Example input file:

CustomerId, Name, Product, Price, Weight, Country
16, Henry Been, Pepernoten, 3.23, 0.5, Netherlands
21, Pietje de Boer, Monitor, 466.19, 2.5, Belgium
16, Henry Been, Jas, 128.12, 2.2, Netherlands

Example output file:

CustomerId, Name, Shipper, Duration, ShippingCost
16, Henry Been, PostNL, 1, 6.95
21, Pietje de Boer, BelgioPosto, 3, 5.7 
