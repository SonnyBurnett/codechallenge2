# Project Object Oriented Programing
## Expert Assignment 3
### Assignment 3: Experts
1. Before you start download the file inputs.csv from https://www.henrybeen.nl/wpcontent/uploads/2020/11/003-experts-inputs.csv
2. Write a program that:
    1. Reads all orders inputs.csv
    2. Writes a file outputs.csv that prints the shipping information per customer using the following rules:
        1. Country = Netherlands and Weight < 10; Shipper PostNL, cost are 6,95
        2. Country = Belgium; Shipper is BelgioPosto and cost are 1,95 + (1 * weight)
        3. All other cases Shipper is DHL and cost are 12,95 + (1.5 * weight)
        4. Duration for PostNL = 1, for BelgioPosto = 2
        5. Duration for DHL ( weight < 10) = 4, DHL (weight >= 10) = 8
    
Remember, the goal of the assignment: write an OO implementation that solves the problem,
 but mostly shows off your OO skills. Do not focus only solving the problem.

### Assignment 3: Experts – example inputs and outputs
Input file:
```
CustomerId, Name, Product, Price, Weight, Country
16, Henry Been, Pepernoten, 3.23, 0.5, Netherlands
21, Pietje de Boer, Monitor, 466.19,2.5, Belgium
16, Henry Been, Jas, 128.12, 2.2, Netherlands
```

Output file:
```
CustomerId, Name, Shipper, Duration, ShippingCost
16, Henry Been, PostNL 1 6.95
21, Pietje de Boer, BelgioPosto 3 5.7
```

## Brainstorm
I started with a simple combination of the previous two exercises, 
during the end of the design I saw that I needed to change the chosen design pattern (The used pattern is that I favour
 is Composite Entity pattern).
In order to make the original deadline I decided to still commit this one (if I have time, or due to the extension of
the assignment I'll switch maybe to the so-favored bridge pattern, however the best pattern to show off will actually 
be the Abstract pattern combined with Filter/Criteria pattern 
(Note: this will be looking a lot as a bridge pattern implementation. So this form will possibly not score the full
points.

Some off the decisions I have taken:
    - Create a customer list with his orders and its packages
    - I introduced some fun into some names of classes and variables (hint: "Pakjes Avond")
    - This time I did not make/use property parser, I simply hardcoded used hard coding for the attributes in and out.
    - I fixed several data issues in the sample data and given examples:
            - inputs files contains a typo in line two 466,19,2.5 should be 466.19,2.5
            - the output of the sample in the pptx at line 3 the shipping cost should be 4.45 and not 5.7
    - The shipping calculation will be done by a shipping stateful engine, with its own component model.

 Here below I show a simplified diagram model on usages and dependencies etc. 
 (note: this is simplified and not a real UML compliant representation, due to ascii art):
```

 +-----------------------------+      +------------------------------+     +-------------------------------+
 | Class : ParcelEvening       |      | Interface : ReadFileInterface|     | Interface : WriteFileInterface|
 +------------+----------------+      +-------------+-----------------+    +-------------+-----------------+
              |                                     |                                    |
              |                      +--------------+------------------------------------+
              |                      |
              |      +---------------------------+       +-----------------------------+
              +------+ Class : Shipping          +-------+ Class : Customers           |
                     +---------------------------+       +-------------+---------------+
                                     |                                 |
              +----------------------+                                 |
              |                                                        |
 +------------+----------------+                         +-------------+---------------+
 | Class : ShippingInformation |                         | Class : Customer            |
 +------------+----------------+                         +-------------+---------------+
              |                                                        |
              +-------------------------------------+------------------+-----------------+
              |                                     |                                    |
 +------------+----------------+      +-------------+---------------+      +-------------+---------------+
 | Class : ShippingRules       |      | Class : Orders              |      | Class : Packages            |
 +------------+----------------+      +-------------+---------------+      +-------------+---------------+
              |                                     |                                    |
              |                                     |                                    |
              |                                     |                                    |
 +------------+----------------+      +-------------+---------------+      +-------------+---------------+
 | Class : ShippingRule        |      | Class : Order               |      | Class : Package             |
 +-----------------------------+      +-----------------------------+      +-----------------------------+


```